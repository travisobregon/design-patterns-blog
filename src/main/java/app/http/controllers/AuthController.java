package app.http.controllers;

import app.accounts.providers.GitHub;
import app.accounts.providers.GitLab;
import app.helpers.ViewMap;
import app.models.User;
import routes.RouteResponse;
import spark.Request;
import spark.Response;

import java.io.IOException;

public class AuthController extends Controller {
    public RouteResponse login(Request request, Response response) throws IOException {
        return new RouteResponse(
            configuration.getTemplate("views/auth/login.ftl"),
            ViewMap.getInstance().with("contextPath", "/login")
        );
    }

    public RouteResponse logout(Request request, Response response) throws IOException {
        ViewMap.getInstance().put("user", null);
        response.redirect("/");

        return null;
    }

    public RouteResponse redirectToGitHub(Request request, Response response) throws IOException {
        new GitHub().authorize(response);

        return null;
    }

    public RouteResponse handleGitHubCallback(Request request, Response response) throws IOException {
        User user = new GitHub().user(request.queryParams("code"));

        if (user == null) {
            response.redirect("/login");
        } else {
            ViewMap.getInstance().put("user", user);
            response.redirect("/posts");
        }

        return null;
    }

    public RouteResponse redirectToGitLab(Request request, Response response) throws IOException {
        new GitLab().authorize(response);

        return null;
    }

    public RouteResponse handleGitLabCallback(Request request, Response response) throws IOException {
        User user = new GitLab().user(request.queryParams("code"));

        if (user == null) {
            response.redirect("/login");
        } else {
            ViewMap.getInstance().put("user", user);
            response.redirect("/posts");
        }

        return null;
    }
}
