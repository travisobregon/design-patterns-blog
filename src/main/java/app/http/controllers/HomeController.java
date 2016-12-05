package app.http.controllers;

import app.conferences.Conference;
import app.conferences.ConferenceComponent;
import app.conferences.ConferenceType;
import app.helpers.ViewMap;
import app.presenters.ConferencePresenter;
import app.tutorials.AbstractTutorialFactory;
import app.tutorials.CountingTutorialFactory;
import app.tutorials.Tutorial;
import app.tutorials.TutorialCounter;
import routes.RouteResponse;
import spark.Request;
import spark.Response;

import java.io.IOException;

public class HomeController extends Controller {
    public RouteResponse home(Request request, Response response) throws IOException {
        this.getTutorials();

        return new RouteResponse(
            configuration.getTemplate("views/home.ftl"),
            ViewMap.getInstance().with("contextPath", "/")
                                 .with("conferences", new ConferencePresenter(getConferences()))
        );
    }

    private ConferenceComponent getConferences() {
        ConferenceComponent devOpsConferences = new ConferenceType("<h4 class=\"card-header text-center\">DevOps Conferences</h4>");
        ConferenceComponent webConferences = new ConferenceType("<h4 class=\"card-header text-center\">Web Conferences</h4>");
        ConferenceComponent phpConferences = new ConferenceType("<h4 class=\"card-header text-center\">PHP Conferences</h4>");
        ConferenceComponent rubyConferences = new ConferenceType("<h4 class=\"card-header text-center\">Ruby Conferences</h4>");
        ConferenceComponent allConferences = new ConferenceType("<h1 class=\"text-center mb-1\">Upcoming Conferences</h1>");

        devOpsConferences.add(new Conference("BMC Engage", "Las Vegas, Nevada", "September 6-9 2017"));
        devOpsConferences.add(new Conference("Monitorama", "Portland, Oregon", "June 27-29 2017"));
        devOpsConferences.add(new Conference("SCALE 14x", "Pasadena, California", "January 21-24 2017"));

        webConferences.add(new Conference("UX Alive", "Istanbul, Turkey", "May 11-13 2017"));
        webConferences.add(new Conference("Web Visions", "London, England", "October 29 2017"));

        phpConferences.add(new Conference("Laracon", "New York, New York", "July 25-26 2017"));
        phpConferences.add(new Conference("ZendCon", "Las Vegas, Nevada", "October 18-21 2017"));

        rubyConferences.add(new Conference("RailsConf", "Phoenix, Arizona", "April 25-27 2017"));

        webConferences.add(phpConferences);
        webConferences.add(rubyConferences);

        allConferences.add(devOpsConferences);
        allConferences.add(webConferences);

        return allConferences;
    }

    private void getTutorials() {
        AbstractTutorialFactory tutorialFactory = new CountingTutorialFactory();
        Tutorial graphQL = tutorialFactory.createBlogTutorial();
        Tutorial laravel = tutorialFactory.createVideoTutorial();
        Tutorial vue = tutorialFactory.createVideoTutorial();
        Tutorial rollup = tutorialFactory.createBlogTutorial();

        graphQL.add("GraphQL", "http://graphql.org/learn/");
        laravel.add("Laravel", "https://laracasts.com/series/laravel-5-from-scratch", "1:36:25");
        vue.add("Vue.js", "https://laracasts.com/series/learn-vue-2-step-by-step", "1:12:48");
        rollup.add("Rollup.js", "https://laravel-news.com/introduction-to-rollup-js");

        ViewMap.getInstance().put("numberOfTutorials", TutorialCounter.getNumberOfTutorials());
    }
}
