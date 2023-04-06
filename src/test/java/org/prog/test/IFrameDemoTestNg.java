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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class IFrameDemoTestNg {

  private WebDriver webDriver;

  @BeforeClass
  public void setUp() {
    webDriver = getDriver();
    loadSchoolsPage();
  }

  @BeforeMethod
  public void beforeEach() {
    loadSchoolsPage();
  }

  @Test
  public void testDE() throws InterruptedException {
    List<WebElement> runButtonOuter = webDriver.findElements(By.id("runbtn"));
    WebElement iframe = webDriver.findElement(By.id("iframeResult"));
    webDriver.switchTo().frame(iframe);
    List<WebElement> runButtonInner = webDriver.findElements(By.id("runbtn"));
    List<WebElement> elements = webDriver.findElements(By.tagName("h1"));
    System.out.println(elements.size());
    System.out.println(runButtonOuter.size());
    System.out.println(runButtonInner.size());
    webDriver.switchTo().defaultContent();
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

  private void loadSchoolsPage() {
    webDriver.get("about::blank");
    webDriver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_iframe_frameborder_css");
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
