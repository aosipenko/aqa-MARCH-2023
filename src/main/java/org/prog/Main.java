package org.prog;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://google.com/");

        List<WebElement> buttons = webDriver.findElements(By.tagName("button"));
        if (buttons.size() == 5) {
            WebElement acceptCookiesButton = buttons.get(3);
            acceptCookiesButton.click();
        } else {
            System.out.println("No cookies form is displayed!");
        }

        webDriver.quit();
    }
}