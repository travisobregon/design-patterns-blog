package app.registration;

import app.helpers.ViewMap;

public class EmailProvided extends RegistrationChecker {
    @Override
    public void check(String email) {
        if (email == null) {
            ViewMap.getInstance().put("error", "You have not made your email public using your account provider.");
            return;
        }

        this.next(email);
    }
}
