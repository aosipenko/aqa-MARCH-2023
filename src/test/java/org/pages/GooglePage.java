package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.prog.cucumber.CucumberHooks;

import java.util.List;
import java.util.Objects;

public class GooglePage {

    private static final String URL = "https://google.com/";

    private WebDriver webDriver;

    public GooglePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void loadPage() {
        if (Objects.isNull(webDriver)) {
            webDriver = CucumberHooks.getDriver();
        }
        webDriver.get("about::blank");
        webDriver.get(URL);
    }

    public void acceptCookies() {
        List<WebElement> buttons = webDriver.findElements(By.tagName("button"));
        if (buttons.size() == 5) {
            buttons.get(3).click();
        }
    }

    public void appendSearchText(String searchValue) {
        webDriver.findElement(By.name("q")).sendKeys(searchValue);
    }

    public void performSearch() {
        webDriver.findElement(By.name("q")).sendKeys(Keys.ENTER);
    }

    public List<String> getSearchResultHeaders() {
        return List.of();
    }

    public void quitDriver() {
        webDriver.quit();
    }
}
