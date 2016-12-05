package app.tutorials;

import app.helpers.ViewMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VideoTutorial implements Tutorial {
    private String title;
    private String url;
    private String duration;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public void add(String... properties) {
        Map<String, Object> viewMap = ViewMap.getInstance();
        VideoTutorial tutorial = new VideoTutorial();

        tutorial.setTitle(properties[0]);
        tutorial.setUrl(properties[1]);
        tutorial.setDuration(properties[2]);

        viewMap.putIfAbsent("tutorials", new ArrayList<>());
        List<Tutorial> tutorials = (ArrayList<Tutorial>) viewMap.get("tutorials");
        tutorials.add(tutorial);

        viewMap.put("tutorials", tutorials);
    }
}
