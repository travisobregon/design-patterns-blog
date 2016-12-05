import app.accounts.providers.GitHub;
import app.accounts.providers.GitLab;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static routes.Router.registerFilters;
import static routes.Router.registerRoutes;
import static spark.Spark.staticFileLocation;

public class Main {
    public static void main(String[] args) throws IOException {
        FileInputStream envFile = new FileInputStream( ".env");
        Properties properties = new Properties(System.getProperties());
        properties.load(envFile);

        System.setProperties(properties);

        GitHub.setClientId(System.getProperty("github.clientId"));
        GitHub.setClientSecret(System.getProperty("github.clientSecret"));
        GitLab.setClientId(System.getProperty("gitlab.clientId"));
        GitLab.setClientSecret(System.getProperty("gitlab.clientSecret"));

        staticFileLocation("/assets");

        registerFilters();
        registerRoutes();
    }
}