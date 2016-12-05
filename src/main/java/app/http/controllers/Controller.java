package app.http.controllers;

import freemarker.template.Configuration;
import freemarker.template.Version;

abstract public class Controller {
    final static Configuration configuration;

    static {
        configuration = new Configuration(new Version(2, 3, 25));
        configuration.setClassForTemplateLoading(Controller.class, "/");
    }
}
