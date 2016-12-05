package app.conferences;

public abstract class ConferenceComponent {
    public void add(ConferenceComponent conferenceComponent) {
        throw new UnsupportedOperationException();
    }

    public void remove(ConferenceComponent conferenceComponent) {
        throw new UnsupportedOperationException();
    }

    public ConferenceComponent getChild(int i) {
        throw new UnsupportedOperationException();
    }

    public String getName() {
        throw new UnsupportedOperationException();
    }

    public String getLocation() {
        throw new UnsupportedOperationException();
    }

    public String getDate() {
        throw new UnsupportedOperationException();
    }

    public String display() {
        throw new UnsupportedOperationException();
    }
}
