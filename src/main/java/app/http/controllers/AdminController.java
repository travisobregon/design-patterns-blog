package app.http.controllers;

import app.helpers.ViewMap;
import app.models.Post;
import app.models.User;
import database.Collection;
import database.DB;
import routes.RouteResponse;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.Map;

public class AdminController extends Controller {
    public RouteResponse publishPosts(Request request, Response response) throws IOException {
        DB DB = new DB();
        Collection<Post> posts = DB.query()
                                   .select("*")
                                   .from("posts")
                                   .where("published_at", "is", null)
                                   .get(Post.class);

        Map<String, Object> map = ViewMap.getInstance()
                .with("contextPath", "/admin/publish-posts")
                .with("posts", posts.getItems());

        return new RouteResponse(configuration.getTemplate("views/admin/publish-posts.ftl"), map);
    }

    public RouteResponse trustUsers(Request request, Response response) throws IOException {
        DB DB = new DB();
        Collection<User> users = DB.query()
                                   .select("*")
                                   .from("users")
                                   .where("trusted", "=", 0)
                                   .get(User.class);

        Map<String, Object> map = ViewMap.getInstance()
                .with("contextPath", "/admin/trust-users")
                .with("users", users.getItems());

        return new RouteResponse(configuration.getTemplate("views/admin/trust-users.ftl"), map);
    }
}
