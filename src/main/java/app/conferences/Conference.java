package app.conferences;

public class Conference extends ConferenceComponent {
    private String name;
    private String location;
    private String date;

    public Conference(String name, String location, String date) {
        this.name = name;
        this.location = location;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String display() {
        return "<p class=\"mb-0 conference-details\">" +
                   "<i class=\"fa fa-users\" aria-hidden=\"true\"></i>" + name +
               "</p>" +
               "<p class=\"mb-0 conference-details\">" +
                   "<i class=\"fa fa-map-marker\" aria-hidden=\"true\"></i>" + location +
               "</p>" +
               "<p class=\"mb-0 conference-details border-bottom\">" +
                   "<i class=\"fa fa-calendar\" aria-hidden=\"true\"></i>" + date +
               "</p>";
    }
}
