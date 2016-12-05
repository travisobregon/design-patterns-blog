package app.registration;

abstract class RegistrationChecker {
    private RegistrationChecker successor;

    public abstract void check(String email);

    public void succeedWith(RegistrationChecker successor) {
        this.successor = successor;
    }

    public void next(String email) {
        if (this.successor != null) {
            this.successor.check(email);
        }
    }
}
