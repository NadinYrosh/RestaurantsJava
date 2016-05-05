import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class CuisineTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

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

  @Test
  public void getRestaurants_retrieveAllRestaurantsInCuisineFromDatabase_true(){
    Cuisine myCuisine = new Cuisine("Indian");
    Restaurant firstRestaurant = new Restaurant ("Luca's", "11-7", myCuisine.getId());
    firstRestaurant.save();
    Restaurant secondRestaurant = new Restaurant ("Mark's", "11-7", myCuisine.getId());
    secondRestaurant.save();
    Restaurant[] restaurants = new Restaurant[] {firstRestaurant,secondRestaurant};
    assertTrue(myCuisine.getRestaurants().containsAll(Arrays.asList(restaurants)));
  }
}
