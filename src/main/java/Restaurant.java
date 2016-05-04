import org.sql2o.*;
import java.util.Arrays;


public class Restaurant{
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


}
