package org.prog.steps;

import io.cucumber.java.en.Given;
import java.util.List;
import java.util.Objects;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.prog.cucumber.CucumberHooks;

public class WebSteps {

  public static WebDriver webDriver;

  @Given("Load URL {string}")
  public void loadPage(String url) {
    loadGooglePage(url);
    acceptCookies();
  }

  @Given("Search for active user")
  public void searchForActiveUser() {
    webDriver.findElement(By.name("q")).sendKeys(
        SqlSteps.activeUser.getName().getFirst() + " " + SqlSteps.activeUser.getName().getLast()
    );

    webDriver.findElement(By.name("q")).sendKeys(Keys.ENTER);
  }

  private void acceptCookies() {
    List<WebElement> buttons = webDriver.findElements(By.tagName("button"));
    if (buttons.size() == 5) {
      buttons.get(3).click();
    }
  }

  private void loadGooglePage(String url) {
    if (Objects.isNull(webDriver)) {
      webDriver = CucumberHooks.getDriver();
    }
    webDriver.get("about::blank");
    webDriver.get(url);
  }
}
