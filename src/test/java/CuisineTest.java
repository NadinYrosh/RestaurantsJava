import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class CuisineTest {

  // @Before
  // public void setUp() {
  //   DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/best_restaurants_test", null, null);
  // }
  //
  // @After
  // public void tearDown() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String deleteCuisineQuery = "DELETE FROM cuisine *;";
  //     String deleteRestaurantsQuery = "DELETE FROM restaurants *;";
  //     con.createQuery(deleteTasksQuery).executeUpdate();
  //     con.createQuery(deleteCategoriesQuery).executeUpdate();
  //   }
  // }

  public void Cuisine_InstantiatesCorrectly_true(){
    Cuisine newCuisine = new Cuisine("Indian");
    assertTrue(newCuisine instanceof Cuisine);
  }

  public void Cuisine_returnNameCorrectly_name(){
    Cuisine newCuisine = new Cuisine("Indian");
    assertEquals ("Indian", newCuisine.getName());
  }


}
