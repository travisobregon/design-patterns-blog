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
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

public class PostsController extends Controller {
    public RouteResponse index(Request request, Response response) throws IOException {
        DB DB = new DB();
        Collection<Post> posts = DB.query()
                                   .select("*")
                                   .from("posts")
                                   .where("published_at", "is not", null)
                                   .orderBy("published_at", "desc")
                                   .get(Post.class);

        Map<String, Object> map = ViewMap.getInstance()
                                         .with("contextPath", "/posts")
                                         .with("posts", posts.getItems());

        return new RouteResponse(configuration.getTemplate("views/posts/index.ftl"), map);
    }

    public RouteResponse create(Request request, Response response) throws IOException {
        return new RouteResponse(
            configuration.getTemplate("views/posts/create.ftl"),
            ViewMap.getInstance().with("contextPath", "/posts/create")
        );
    }

    public RouteResponse store(Request request, Response response) throws IOException {
        DB DB = new DB();
        User user = (User) ViewMap.getInstance().get("user");
        Long date = null;

        if (user.getIsAdmin() || user.getIsTrusted()) {
            date = new Date().getTime();
        }

        DB.query().insert("posts", Arrays.asList(
            user.getId(),
            request.queryParams("title"),
            request.queryParams("body"),
            date
        ));

        response.redirect("/posts");

        return null;
    }

    public RouteResponse publishPost(Request request, Response response) throws IOException {
        Integer postId = Integer.parseInt(request.params(":id"));
        Long date = new Date().getTime();
        DB DB = new DB();

        DB.query().update(
            "posts",
            "published_at = " + String.valueOf(date),
            "id = " + String.valueOf(postId)
        );

        response.redirect("/admin/publish-posts");

        return null;
    }
}
