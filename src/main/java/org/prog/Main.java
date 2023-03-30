package org.prog;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    WebDriver webDriver = null;
    Thread.sleep(15000);
    try {
      webDriver = getDriver();
      loadCloudFlarePage(webDriver);
      waitAndAcceptCookies(webDriver);
      acceptCookies(webDriver);
      selectLanguage(webDriver, "Deutsch");

      new WebDriverWait(webDriver, Duration.ofSeconds(60))
          .until(ExpectedConditions.textToBePresentInElementLocated(By.className("headline-1"), "Ein globales Netzwerk für die Cloud"));
      System.out.println(webDriver.findElement(By.className("headline-1")).getText());
    } finally {
      if (webDriver != null) {
        webDriver.quit();
      }
    }
  }

  private static void loadCloudFlarePage(WebDriver webDriver) {
    webDriver.get("about::blank");
    webDriver.get("https://www.cloudflare.com/");
  }

  private static WebDriver getDriver() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--remote-allow-origins=*");
    return new ChromeDriver(chromeOptions);
  }

  private static void waitAndAcceptCookies(WebDriver webDriver) {
    new WebDriverWait(webDriver, Duration.ofSeconds(45))
        .until(ExpectedConditions.presenceOfElementLocated(By.id("onetrust-policy-text")));
    new WebDriverWait(webDriver, Duration.ofSeconds(5))
        .until(ExpectedConditions.elementToBeClickable(By.id("onetrust-accept-btn-handler")));
  }

  private static void selectLanguage(WebDriver driver, String language) {
    driver.findElements(By.className("newNav-button")).get(2).click();
    List<WebElement> dropDownOptions = driver.findElements(By.className("cf-dropdown")).get(2)
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

  private static void acceptCookies(WebDriver driver) {
    for (int i = 0; i < 5; i++) {
      try {
        new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(ExpectedConditions.elementToBeClickable(By.id("onetrust-accept-btn-handler")))
            .click();
        return;
      } catch (WebDriverException we) {
        System.err.println("Failed to click element, will try again");
      }
    }
    throw new RuntimeException("Failed to click element");
  }
}