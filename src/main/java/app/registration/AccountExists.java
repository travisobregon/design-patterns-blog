package app.registration;

import app.helpers.ViewMap;
import app.models.User;
import database.DB;

public class AccountExists extends RegistrationChecker {
    @Override
    public void check(String email) {
        DB DB = new DB();

        User user = (User) DB.query()
                             .select("*")
                             .from("users")
                             .where("email", "=", email)
                             .first(User.class);

        if (user != null) {
            ViewMap.getInstance().put(
                "error",
                "You have already registered using a different provider with the email " + email + "."
            );

            return;
        }

        this.next(email);
    }
}
