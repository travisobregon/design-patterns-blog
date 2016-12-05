package app.presenters;

import app.conferences.ConferenceComponent;

public class ConferencePresenter {
    private ConferenceComponent allConferences;

    public ConferencePresenter(ConferenceComponent allConferences) {
        this.allConferences = allConferences;
    }

    public String displayConferences() {
        return allConferences.display();
    }
}
