package app.tutorials;

import app.helpers.ViewMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlogTutorial implements Tutorial {
    private String title;
    private String url;

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

    @Override
    public void add(String... properties) {
        Map<String, Object> viewMap = ViewMap.getInstance();
        BlogTutorial tutorial = new BlogTutorial();

        tutorial.setTitle(properties[0]);
        tutorial.setUrl(properties[1]);

        viewMap.putIfAbsent("tutorials", new ArrayList<>());
        List<Tutorial> tutorials = (ArrayList<Tutorial>) viewMap.get("tutorials");
        tutorials.add(tutorial);

        viewMap.put("tutorials", tutorials);
    }
}
