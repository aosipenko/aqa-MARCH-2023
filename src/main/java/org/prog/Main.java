package org.prog;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    WebDriver webDriver = null;
    Thread.sleep(15000);
    try {
      webDriver = getDriver();

      loadGooglePage(webDriver);
      acceptCookiesIfVisible(webDriver);
      googleSearch(webDriver, "Amazon");
      validateSearch(webDriver, "Amazon");

      loadGooglePage(webDriver);
      googleSearch(webDriver, "Facebook");
      validateSearch(webDriver, "Facebook");

      loadGooglePage(webDriver);
      googleSearch(webDriver, "LinkedIn");
      validateSearch(webDriver, "LinkedIn");

      loadGooglePage(webDriver);
      googleSearch(webDriver, "Cloudflare");
      validateSearch(webDriver, "Cloudflare");
    } finally {
      if (webDriver != null) {
        webDriver.quit();
      }
    }
  }

  private static void loadGooglePage(WebDriver webDriver){
    webDriver.get("about::blank");
    webDriver.get("https://google.com/");
  }

  private static WebDriver getDriver() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--remote-allow-origins=*");
    return new ChromeDriver(chromeOptions);
  }

  private static void validateSearch(WebDriver webDriver, String searchValue) {
    List<WebElement> searchResultHeaders = webDriver.findElements(By.xpath("//a/h3"));
    List<String> headerList = new ArrayList<>();

    for (WebElement searchHeader : searchResultHeaders) {
      headerList.add(searchHeader.getText());
    }

    for (String headerText : headerList) {
      if (headerText.contains(searchValue)) {
        System.out.println("Search is a success!");
        break;
      }
    }

//    List<String> headers = webDriver.findElements(By.xpath("//a/h3")).stream()
//        .map(webElement -> {
//          return webElement.getText();
//        })
//        .map(elementText -> {
//          System.out.println(elementText);
//          return elementText;
//        })
//        .collect(Collectors.toList());

//    List<String> headers = webDriver.findElements(By.xpath("//a/h3")).stream()
//        .map(webElement -> webElement.getText())
//        .filter(elementText -> elementText != null && elementText.length() > 0)
//        .collect(Collectors.toList());

//    System.out.println(headers.size());
  }

  private static void googleSearch(WebDriver webDriver, String searchValue) {
    WebElement searchBar = webDriver.findElement(By.name("q"));
    searchBar.sendKeys(searchValue);
    searchBar.sendKeys(Keys.ENTER);
  }

  private static void acceptCookiesIfVisible(WebDriver webDriver) {
    List<WebElement> buttons = webDriver.findElements(By.tagName("button"));
    if (buttons.size() == 5) {
      WebElement acceptCookiesButton = buttons.get(3);
      acceptCookiesButton.click();
    } else {
      System.out.println("No cookies form is displayed!");
    }
  }
}