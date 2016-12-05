package app.listeners;

import app.events.UserSubject;
import database.DB;

public class UpdateUser implements Observer {
    private UserSubject userIsTrusted;

    public UpdateUser(UserSubject userIsTrusted) {
        this.userIsTrusted = userIsTrusted;
        this.userIsTrusted.registerObserver(this);
    }

    @Override
    public void handle() {
        DB DB = new DB();

        DB.query().update(
            "users",
            "trusted = 1",
            "id = " + String.valueOf(this.userIsTrusted.getUserId())
        );
    }
}
