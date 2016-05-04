import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;


public class RestaurantTest {


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



}
