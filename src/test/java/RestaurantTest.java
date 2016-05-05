import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;


public class RestaurantTest {

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
  public void Restaurant_InstantiatesCorrectly_true(){
    Restaurant restaurant = new Restaurant("Luca's", "11-7", 1);
    assertTrue(restaurant instanceof Restaurant);
  }

  @Test
  public void getName_gettingTheName_String(){
    Restaurant restaurant = new Restaurant("Luca's", "11-7", 1);
    assertEquals("Luca's", restaurant.getName());
  }

  @Test
  public void getHours_gettingTheHours_String(){
    Restaurant restaurant = new Restaurant("Luca's", "11-7", 1);
    assertEquals("11-7", restaurant.getHours());
  }

  @Test
  public void getCuisineId_gettingTheId_Int(){
    Restaurant restaurant = new Restaurant("Luca's", "11-7", 1);
    assertEquals(1, restaurant.getCuisineId());
  }

  @Test
  public void all_emptyAtFirst(){
    assertEquals(Restaurant.all().size(), 0);
  }

  @Test
  public void save_restaurantIsSavedRestaurant(){
    Restaurant newRestaurant = new Restaurant("Luca's", "11-7", 1);
    newRestaurant.save();
    assertTrue(newRestaurant.getId() == Restaurant.all().get(0).getId());
  }

  @Test
  public void find_findsRestaurantInDatabase_True() {
    Restaurant myRestaurant = new Restaurant("Luca's", "11-7", 1);
    myRestaurant.save();
    Restaurant savedRestaurant = Restaurant.find(myRestaurant.getId());
    assertTrue(myRestaurant.equals(savedRestaurant));
  }

  @Test
  public void save_savesCuisineIdIntoDB_true() {
    Cuisine myCuisine = new Cuisine("Indian");
    myCuisine.save();
    Restaurant myRestaurant = new Restaurant("Luca's", "11-7", myCuisine.getId());
    myRestaurant.save();
    Restaurant savedRestaurant = Restaurant.find(myRestaurant.getId());
    assertEquals(savedRestaurant.getCuisineId(), myCuisine.getId());
  }
}
