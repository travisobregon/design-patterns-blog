package routes;

import freemarker.template.Template;

import java.util.Map;

public class RouteResponse {
    private Template template;
    private Map<String, Object> map;

    public RouteResponse(Template template, Map<String, Object> map) {
        this.template = template;
        this.map = map;
    }

    public Template getTemplate() {
        return template;
    }

    public Map<String, Object> getMap() {
        return map;
    }
}
