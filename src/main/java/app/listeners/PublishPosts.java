package app.listeners;

import app.events.UserSubject;
import database.DB;

import java.util.Date;

public class PublishPosts implements Observer {
    private UserSubject userIsTrusted;

    public PublishPosts(UserSubject userIsTrusted) {
        this.userIsTrusted = userIsTrusted;
        this.userIsTrusted.registerObserver(this);
    }

    @Override
    public void handle() {
        Long date = new Date().getTime();
        DB DB = new DB();

        DB.query().update(
            "posts",
            "published_at = " + String.valueOf(date),
            "user_id = " + String.valueOf(this.userIsTrusted.getUserId()) + " and published_at is null"
        );
    }
}
