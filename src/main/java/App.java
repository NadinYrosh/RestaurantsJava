import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;
import java.util.List;

public class App {

  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("/cuisines/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/cuisine-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/cuisines", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      Cuisine newCuisine = new Cuisine(name);
      newCuisine.save();
      model.put("template", "templates/succes.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/cuisines", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<Cuisine> cuisines = Cuisine.all();
      model.put("cuisines", cuisines);
      model.put("template", "templates/cuisines.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/cuisines/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Cuisine newCuisine = Cuisine.find(Integer.parseInt(request.params(":id")));

      // List<Restaurant> restaurants = Cuisine.getRestaurants();
      model.put("restaurants", newCuisine.getRestaurants());
      model.put("template", "templates/cuisine.vtl");
      model.put("cuisine", newCuisine);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/cuisines/:id/restaurants", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Cuisine newCuisine = Cuisine.find(Integer.parseInt(request.params(":id")));
      // String tempName = request.queryParams("restaurant"); - debugger
      // System.out.println(tempName);- debugger
      Restaurant newRestaurant = new Restaurant (request.queryParams("restaurant"), request.queryParams("hours"), newCuisine.getId());
      newRestaurant.save();
      System.out.println(newCuisine.getRestaurants().get(0).getName());
      model.put("restaurants", newCuisine.getRestaurants());
      model.put("template", "templates/cuisine.vtl");
      model.put("cuisine", newCuisine);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
