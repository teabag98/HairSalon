import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


public class App {

    public static void main(String[] args) {
        staticFileLocation("/public");
        String layout = "templates/layout.vtl";

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/index.vtl");
            model.put("stylists", Stylists.all());
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("stylists/:id/tasks/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylists stylist = Stylists.find(Integer.parseInt(request.params(":id")));
            model.put("stylist", stylist);
            model.put("template", "templates/stylist-client-form.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/clients", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("clients", Clients.all());
            model.put("template", "templates/clients.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/clients", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylists stylist =Stylists.find(Integer.parseInt(request.queryParams("stylistId")));
            String name = request.queryParams("name");
            Clients newClient = new Clients(name, stylist.getId());
            newClient.save();
            model.put("stylist", stylist);
            model.put("template", "templates/stylist-client-success.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


        get("/clients/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Clients client = Clients.find(Integer.parseInt(request.params(":id")));
            model.put("client", client);
            model.put("template", "templates/client.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/stylists/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/stylist-form.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/stylists", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            String description = request.queryParams("description");
            Stylists newStylist = new Stylists(name,description);
            newStylist.save();
            model.put("template", "templates/stylist-success.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/stylists", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("stylists", Stylists.all());
            model.put("template", "templates/stylists.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/stylists/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylists stylist = Stylists.find(Integer.parseInt(request.params(":id")));
            model.put("stylist", stylist);
            model.put("template", "templates/stylist.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());




    }
}
