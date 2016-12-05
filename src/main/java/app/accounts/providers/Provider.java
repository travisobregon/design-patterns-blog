package app.accounts.providers;

import app.helpers.ViewMap;
import app.models.User;
import app.registration.AccountExists;
import app.registration.EmailProvided;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import spark.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

abstract class Provider {
    abstract protected String getAuthorizationUrl();

    abstract protected String getAccessTokenUrl(String code);

    abstract protected JsonObject getUserByToken(String token) throws IOException;

    abstract protected User mapToUser(JsonObject jsonObject);

    abstract protected User createNewUser(JsonObject jsonObject);

    private String requestAccessToken(String code) throws IOException {
        URL url = new URL(this.getAccessTokenUrl(code));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            return new Gson().fromJson(response.toString(), JsonObject.class).get("access_token").getAsString();
        }

        System.out.println("Unable to fetch access token.");

        return null;
    }

    JsonObject handleHttpOkResponse(HttpURLConnection connection) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();

        return new Gson().fromJson(response.toString(), JsonObject.class);
    }

    public void authorize(Response response) {
        response.redirect(this.getAuthorizationUrl());
    }

    public User user(String code) throws IOException {
        String token = this.requestAccessToken(code);

        JsonObject jsonObject = this.getUserByToken(token);

        return this.mapToUser(jsonObject);
    }

    public boolean isValidRegistration(String email) {
        EmailProvided emailProvided = new EmailProvided();
        AccountExists accountExists = new AccountExists();

        emailProvided.succeedWith(accountExists);
        emailProvided.check(email);

        if (ViewMap.getInstance().containsKey("error")) {
            return false;
        }

        return true;
    }
}
