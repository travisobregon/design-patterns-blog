package app.http.controllers;

import app.events.UserIsTrusted;
import app.listeners.PublishPosts;
import app.listeners.UpdateUser;
import routes.RouteResponse;
import spark.Request;
import spark.Response;

import java.io.IOException;

public class UsersController extends Controller {
    public RouteResponse trustUser(Request request, Response response) throws IOException {
        UserIsTrusted userIsTrusted = new UserIsTrusted(Integer.parseInt(request.params(":id")));
        UpdateUser updateUser = new UpdateUser(userIsTrusted);
        PublishPosts publishPosts = new PublishPosts(userIsTrusted);

        userIsTrusted.notifyObservers();
        response.redirect("/admin/trust-users");

        return null;
    }
}
