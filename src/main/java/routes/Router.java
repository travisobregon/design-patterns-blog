package routes;

import app.helpers.ViewMap;
import app.http.controllers.*;
import app.models.User;

import static spark.Spark.*;

public class Router {
    public static void registerFilters() {
        before("/admin/*", (request, response) -> {
            User user = (User) ViewMap.getInstance().get("user");

            if (user == null || ! user.getIsAdmin()) {
                halt(401, "You are not welcome here");
            }
        });

        after((request, response) -> {
            ViewMap.getInstance().remove("error");
            ViewMap.getInstance().remove("tutorials");
        });
    }

    public static void registerRoutes() {
        /* Home */
        Route.get("/", new HomeController(), "home");

        /* Auth */
        AuthController authController = new AuthController();

        Route.get("/login", authController, "login");
        Route.get("/logout", authController, "logout");
        Route.get("/auth/github", authController, "redirectToGitHub");
        Route.get("/auth/github/callback", authController, "handleGitHubCallback");
        Route.get("/auth/gitlab", authController, "redirectToGitLab");
        Route.get("/auth/gitlab/callback", authController, "handleGitLabCallback");

        /* Posts */
        PostsController postsController = new PostsController();

        Route.get("/posts", postsController, "index");
        Route.get("/posts/create", postsController, "create");
        Route.post("/posts", postsController, "store");
        Route.post("/posts/:id", postsController, "publishPost");

        /* Users */
        UsersController usersController = new UsersController();

        Route.post("/users/:id", usersController, "trustUser");

        /* Admin */
        AdminController adminController = new AdminController();

        Route.get("/admin/publish-posts", adminController, "publishPosts");
        Route.get("/admin/trust-users", adminController, "trustUsers");
    }
}
