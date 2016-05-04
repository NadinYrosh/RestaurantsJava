import org.sql2o.*;
import java.util.Arrays;
import java.util.List;



public class Restaurant{
  private int id;
  private int cuisine_id;
  private String name;
  private String hours;

  public Restaurant(String name, String hours,int cuisine_id){
    this.cuisine_id = cuisine_id;
    this.name = name;
    this.hours = hours;
  }

  public String getName(){
    return name;
  }

  public String getHours(){
    return hours;
  }

  public int getCuisineId(){
    return cuisine_id;
  }


  public int getId(){
    return id;
  }


  public static List<Restaurant> all(){
    String sql = "SELECT id,name FROM restaurants";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Restaurant.class);
    }
  }

  @Override
  public boolean equals(Object otherRestaurant){
    if (!(otherRestaurant instanceof Restaurant)){
      return false;
    } else {
      Restaurant newRestaurant = (Restaurant) otherRestaurant;
      return newRestaurant.getName()
             .equals(this.getName()) &&
             newRestaurant.getCuisineId() == (this.getCuisineId());
    }
  }

  public void save(){
    String sql = "INSERT INTO restaurants (name, hours, cuisine_id) VALUES (:name, :hours, :cuisine_id)";
    try (Connection con = DB.sql2o.open()){
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("hours", this.hours)
      .addParameter("cuisine_id", this.cuisine_id)
      .executeUpdate()
      .getKey();
    }
  }
  //
  // public static Cuisine find(int id){
  //   try(Connection con = DB.sql2o.open()){
  //     String sql = "SELECT * FROM cuisine WHERE id=:id";
  //     Cuisine cusine =  con.createQuery(sql)
  //       .addParameter("id", id)
  //       .executeAndFetchFirst(Cuisine.class);
  //     return cusine;
  //   }
  // }



}
