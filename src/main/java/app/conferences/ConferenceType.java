package app.conferences;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConferenceType extends ConferenceComponent {
    private List<ConferenceComponent> conferenceComponents;
    private String name;

    public ConferenceType(String name) {
        this.conferenceComponents = new ArrayList<>();
        this.name = name;
    }

    public void add(ConferenceComponent conferenceComponent) {
        conferenceComponents.add(conferenceComponent);
    }

    public void remove(ConferenceComponent conferenceComponent) {
        conferenceComponents.remove(conferenceComponent);
    }

    public ConferenceComponent getChild(int i) {
        return conferenceComponents.get(i);
    }

    public String getName() {
        return name;
    }

    public String display() {
        StringBuilder markup = new StringBuilder(name);
        Iterator<ConferenceComponent> iterator = conferenceComponents.iterator();

        while (iterator.hasNext()) {
            ConferenceComponent conferenceComponent = iterator.next();

            markup.append(conferenceComponent.display());
        }

        return markup.toString();
    }
}
