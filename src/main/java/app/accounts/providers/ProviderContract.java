package app.accounts.providers;

import app.models.User;
import spark.Response;

import java.io.IOException;

public interface ProviderContract {
    void authorize(Response response);
    User user(String code) throws IOException;
}
