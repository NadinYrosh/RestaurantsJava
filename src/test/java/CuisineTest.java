import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class CuisineTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/best_restaurants_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteCuisineQuery = "DELETE FROM cuisine *;";
      String deleteRestaurantsQuery = "DELETE FROM restaurants *;";
      con.createQuery(deleteCuisineQuery).executeUpdate();
      con.createQuery(deleteRestaurantsQuery).executeUpdate();
    }
  }
  @Test
  public void Cuisine_InstantiatesCorrectly_true(){
    Cuisine newCuisine = new Cuisine("Indian");
    assertTrue(newCuisine instanceof Cuisine);
  }

  @Test
  public void Cuisine_returnNameCorrectly_name(){
    Cuisine newCuisine = new Cuisine("Indian");
    assertEquals ("Indian", newCuisine.getName());
  }

  @Test
  public void all_emptyAtFirst(){
    assertEquals(Cuisine.all().size(), 0);
  }

  @Test
  public void save_cuisineIsSavedCorrectly(){
    Cuisine newCuisine = new Cuisine("Indian");
    newCuisine.save();
    assertTrue(newCuisine.equals(Cuisine.all().get(0)));
  }

  @Test
  public void find_CusuineInDataBase_true(){
    Cuisine newCuisine = new Cuisine("Indian");
    newCuisine.save();
    Cuisine savedCuisine = Cuisine.find(newCuisine.getId());
    assertTrue(newCuisine.equals(savedCuisine));
  }

}
