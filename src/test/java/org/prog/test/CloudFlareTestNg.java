package org.prog.test;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CloudFlareTestNg {

  private WebDriver webDriver;

  @BeforeClass
  public void setUp() {
    webDriver = getDriver();
    loadCloudFlarePage();
    acceptCookies();
  }

  @BeforeMethod
  public void beforeEach() {
    loadCloudFlarePage();
  }

  @Test
  public void testDE() throws InterruptedException {
    selectLanguage("Deutsch");
    new WebDriverWait(webDriver, Duration.ofSeconds(60)).until(ExpectedConditions.urlContains("de-de"));
    Thread.sleep(2000);
    Assert.assertEquals(webDriver.findElement(By.className("headline-1")).getText(),
        "Ein globales Netzwerk für die Cloud");
  }

  @Test
  public void testFR() throws InterruptedException {
    selectLanguage("Français");
    new WebDriverWait(webDriver, Duration.ofSeconds(60)).until(ExpectedConditions.urlContains("fr-fr"));
    Thread.sleep(2000);
    Assert.assertEquals(webDriver.findElement(By.className("headline-1")).getText(),
        "Un reseau mondial conçu pour le cloud");
  }

  @Test
  public void testCN() throws InterruptedException {
    selectLanguage("繁體中文");
    new WebDriverWait(webDriver, Duration.ofSeconds(60)).until(ExpectedConditions.urlContains("zh-tw"));
    Thread.sleep(2000);
    Assert.assertEquals(webDriver.findElement(By.className("headline-1")).getText(),
        "為雲而建的全球性網路");
  }

  @AfterMethod
  public void endTest() {
    System.out.println("===============================");
  }

  @AfterClass
  public void tearDown() {
    if (webDriver != null) {
      webDriver.quit();
    }
  }

  private void loadCloudFlarePage() {
    webDriver.get("about::blank");
    webDriver.get("https://www.cloudflare.com/");
  }

  private WebDriver getDriver() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--remote-allow-origins=*");
    return new ChromeDriver(chromeOptions);
  }

  private void selectLanguage(String language) {
    clickLanguageSelect();

    List<WebElement> dropDownOptions = webDriver.findElements(By.className("cf-dropdown")).get(2)
        .findElements(By.xpath("//ul/li/a"));

    Optional<WebElement> languageBtn = dropDownOptions.stream()
        .filter(webElement -> webElement.getText().equals(language))
        .findFirst();

    if (languageBtn.isPresent()) {
      languageBtn.get().click();
    } else {
      throw new RuntimeException("Language button not found!");
    }
  }

  private void acceptCookies() {
    for (int i = 0; i < 5; i++) {
      try {
        new WebDriverWait(webDriver, Duration.ofSeconds(5))
            .until(ExpectedConditions.elementToBeClickable(By.id("onetrust-accept-btn-handler")))
            .click();
        return;
      } catch (WebDriverException we) {
        System.err.println("Failed to click element, will try again");
      }
    }
    throw new RuntimeException("Failed to click element");
  }

  private void clickLanguageSelect() {
    for (int i = 0; i < 5; i++) {
      try {
        new WebDriverWait(webDriver, Duration.ofSeconds(30))
            .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("newNav-button")))
            .get(2).click();
        return;
      } catch (StaleElementReferenceException sere) {
        System.err.println("Element is stale, will try again");
      }
    }
    throw new RuntimeException("Failed to click element");

  }
}
