import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
import org.sql2o.*;
import org.junit.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Cuisines");
  }

  @Test
  public void CuisineIsCreatedTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Add a new cuisine"));
    fill("#name").with("Indian");
    submit(".btn");
    assertThat(pageSource()).contains("Success!");
  }

  @Test
  public void CuisineIsDisplayed() {
    // goTo("http://localhost:4567/");
    // click("a", withText("Add a new cuisine"));
    // fill("#name").with("Indian");
    // submit(".btn");
    Cuisine newCuisine = new Cuisine("Indian");
    newCuisine.save();
    String cuisinesPath = String.format("http://localhost:4567/cuisines");
    goTo(cuisinesPath);
    assertThat(pageSource()).contains("Indian");
  }

  @Test
  public void cuisinesShowPageDisplaysName() {
    Cuisine newCuisine = new Cuisine("Indian");
    newCuisine.save();
    String cuisinesPath = String.format("http://localhost:4567/cuisines");
    goTo(cuisinesPath);
    click("a", withText("Indian"));
    assertThat(pageSource()).contains("Add a new restaurant");
  }

  @Test
    public void restaurantIsAddedAndDisplayed() {
      goTo("http://localhost:4567/cuisines/new");
      fill("#name").with("Indian");
      submit(".btn");
      click("a", withText("View list of cuisines"));
      click("a", withText("Indian"));
      fill("#restaurant").with("Lucka's");
      fill("#hours").with("11-7");
      click("a", withText("Add a new restaurant"));
      submit(".btn");
      assertThat(pageSource()).contains("Lucka's");
    }

}
