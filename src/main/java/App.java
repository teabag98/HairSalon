

        import java.util.Map;
        import java.util.HashMap;
        import spark.ModelAndView;
        import spark.template.velocity.VelocityTemplateEngine;
        import static spark.Spark.*;
        import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String layout = "templates/layout.vtl";

        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        setPort(port);

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("stylists", Stylist.all());
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

// for viewing list of stylists
        get("/stylists", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("stylists", Stylist.all());
            model.put("template", "templates/stylists.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

// for getting stylist form
        get("/stylists/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/stylist-form.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

// for viewing a specific stylist
        get("/stylists/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
            model.put("stylist", stylist);
            model.put("template", "templates/stylist.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

// for getting the client form n Assign to specific stylist
        get("stylists/:id/clients/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
            model.put("stylist", stylist);
            model.put("template", "templates/stylist-clients-form.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

//for viewing specific stylists clients
        get("/stylists/:stylist_id/clients/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylist_id")));
            Client client = Client.find(Integer.parseInt(request.params(":id")));
            model.put("stylist", stylist);
            model.put("client", client);
            model.put("template", "templates/client.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/stylists", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            String description = request.queryParams("description");
            Stylist newStylist = new Stylist(name,description);
            newStylist.save();
            response.redirect("/stylists");
            // model.put("template", "templates/stylist-List.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/stylists/:id/clients", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            String gender = request.queryParams("gender");
            int cellphone = Integer.parseInt(request.queryParams("cellphone"));
            int stylistId = Integer.parseInt(request.params(":id"));

            Client newClient = new Client(name, gender, cellphone, stylistId);
            newClient.save();
            response.redirect("/stylists/" + stylistId);
            // model.put("template", "templates/stylist-clients-form.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/stylists/:stylist_id/clients/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Client client = Client.find(Integer.parseInt(request.params("id")));
            String name = request.queryParams("name");
            String gender = request.queryParams("gender");
            int cellphone = Integer.parseInt(request.queryParams("cellphone"));
            Stylist stylist = Stylist.find(client.getStylistId());
            client.update(name, gender, cellphone);
            String url = String.format("/stylists/%d/clients/%d", stylist.getId(), client.getId());
            response.redirect(url);
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/stylists/:stylist_id/clients/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Client client = Client.find(Integer.parseInt(request.params("id")));
            Stylist stylist = Stylist.find(client.getStylistId());
            client.delete();
            model.put("stylist", stylist);
            model.put("template", "templates/stylist.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());



        post("/stylists/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params("id")));
            // Stylist stylist = Stylist.find(client.getStylistId());

            stylist.delete();
            model.put("stylist", Stylist.all());
            model.put("template", "templates/stylist.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


    }
}
