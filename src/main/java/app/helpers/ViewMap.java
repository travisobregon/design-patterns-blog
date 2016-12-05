package app.helpers;

import java.util.HashMap;

public class ViewMap extends HashMap<String, Object> {
    private static ViewMap instance;

    private ViewMap() {
        super();

        this.put("contextPath", null);
        this.put("user", null);
    }

    public static ViewMap getInstance() {
        if (instance == null) {
            instance = new ViewMap();
        }

        return instance;
    }

    public ViewMap with(String key, Object value) {
        this.put(key, value);

        return this;
    }
}
