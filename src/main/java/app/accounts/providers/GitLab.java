package app.accounts.providers;

import app.models.User;
import com.google.gson.JsonObject;
import database.DB;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class GitLab extends Provider {
    private static String clientId;
    private static String clientSecret;

    @Override
    protected String getAuthorizationUrl() {
        return new StringBuilder("https://gitlab.com/oauth/authorize?")
                .append("client_id=").append(clientId)
                .append("&redirect_uri=http://127.0.0.1:4567/auth/gitlab/callback")
                .append("&response_type=code")
                .toString();
    }

    @Override
    protected String getAccessTokenUrl(String code) {
        return new StringBuilder("https://gitlab.com/oauth/token?")
                .append("client_id=").append(clientId)
                .append("&client_secret=").append(clientSecret)
                .append("&code=").append(code)
                .append("&grant_type=authorization_code")
                .append("&redirect_uri=http://127.0.0.1:4567/auth/gitlab/callback")
                .toString();
    }

    @Override
    protected JsonObject getUserByToken(String token) throws IOException {
        URL url = new URL("https://gitlab.com/api/v3/user");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", "Bearer " + token);

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            return this.handleHttpOkResponse(connection);
        }

        System.out.println("Unable to fetch user using the access token.");

        return null;
    }

    @Override
    protected User mapToUser(JsonObject jsonObject) {
        DB DB = new DB();
        Integer providerId = jsonObject.get("id").getAsInt();
        User user = (User) DB.query()
                             .select("*")
                             .from("users")
                             .where("provider", "=", "gitlab")
                             .andWhere("provider_id", "=", String.valueOf(providerId))
                             .first(User.class);

        if (user != null) {
            return user;
        }

        return this.createNewUser(jsonObject);
    }

    @Override
    protected User createNewUser(JsonObject jsonObject) {
        String email = jsonObject.get("email").getAsString();

        if (! this.isValidRegistration(email)) {
            return null;
        }

        DB DB = new DB();

        DB.query().insert("users", Arrays.asList(
            "gitlab",
            jsonObject.get("id").getAsInt(),
            jsonObject.get("name").getAsString(),
            email,
            jsonObject.get("avatar_url").getAsString(),
            0,
            0
        ));

        return (User) DB.query()
                        .select("*")
                        .from("users")
                        .where("provider", "=", "gitlab")
                        .andWhere("provider_id", "=", jsonObject.get("id").getAsString())
                        .first(User.class);
    }

    public static void setClientId(String value) {
        clientId = value;
    }

    public static void setClientSecret(String value) {
        clientSecret = value;
    }
}
