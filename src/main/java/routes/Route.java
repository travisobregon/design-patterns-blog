package routes;

import app.http.controllers.Controller;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static spark.Spark.halt;

class Route {
    public static void get(String path, Controller controller, String methodName) {
        Spark.get(path, (request, response) -> handle(controller, methodName, request, response));
    }

    public static void post(String path, Controller controller, String methodName) {
        Spark.post(path, (request, response) -> handle(controller, methodName, request, response));
    }

    private static StringWriter handle(Controller controller, String methodName, Request request, Response response) {
        StringWriter writer = new StringWriter();

        try {
            Method method;
            RouteResponse routeResponse = null;

            try {
                method = controller.getClass().getMethod(methodName, Request.class, Response.class);
                routeResponse = (RouteResponse) method.invoke(controller, request, response);
            } catch (SecurityException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }

            if (routeResponse != null) {
                routeResponse.getTemplate().process(routeResponse.getMap(), writer);
            } else {
                throw new Exception("Invalid route registered");
            }
        } catch (Exception e) {
            halt(500);
        }

        return writer;
    }
}
