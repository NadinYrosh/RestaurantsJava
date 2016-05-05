import java.util.List;
import org.sql2o.*;
import java.util.Arrays;

public class Cuisine {
  private String name;
  private int id;



  public Cuisine(String name) {
    this.name = name;
  }

  public String getName(){
    return name;
  }

  public int getId(){
    return id;
  }


  public static List<Cuisine> all(){
    String sql = "SELECT id,name FROM cuisine";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Cuisine.class);
    }
  }

  @Override
  public boolean equals(Object otherCuisine){
    if (!(otherCuisine instanceof Cuisine)){
      return false;
    } else {
      Cuisine newCuisine = (Cuisine) otherCuisine;
      return newCuisine.getName().equals(this.getName()) && newCuisine.getId() == (this.getId());
    }
  }

  public void save(){
    String sql = "INSERT INTO cuisine (name) VALUES (:name)";
    try (Connection con = DB.sql2o.open()){
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .executeUpdate()
      .getKey();
    }
  }

  public static Cuisine find(int id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM cuisine WHERE id=:id";
      Cuisine cusine =  con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Cuisine.class);
      return cusine;
    }
  }

  public List<Restaurant> getRestaurants(){
    try (Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM restaurants WHERE cuisine_id = :cuisine_id";
      return con.createQuery(sql).addParameter("cuisine_id", this.id).executeAndFetch(Restaurant.class);
    }
  }
}
