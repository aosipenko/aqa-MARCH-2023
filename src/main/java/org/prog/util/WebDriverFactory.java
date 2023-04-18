package org.prog.util;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WebDriverFactory {

    private final static WebDriverFactory instance = new WebDriverFactory();
    private final static List<WebDriver> driverList = new ArrayList<>();

    private WebDriverFactory() {
    }

    public static WebDriverFactory getInstance() {
        return instance;
    }

    public WebDriver getDriver(DriverName driverName) {
        switch (driverName) {
            case CHROME_LOCAL:
                return initLocalDriver();
            case CHROME_REMOTE:
                return initRemoteDriver();
            default:
                return initJenkinsDriver();
        }
    }

    public void quitAllDriver() {
        driverList.forEach(wd -> {
            if (wd != null) {
                wd.quit();
            }
        });
    }

    @SneakyThrows
    private WebDriver initJenkinsDriver() {
        return initAndStore(new RemoteWebDriver(new URL("http://selenoid:4444/wd/hub"), options()));
    }

    @SneakyThrows
    private WebDriver initRemoteDriver() {
        return initAndStore(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options()));
    }

    @SneakyThrows
    private WebDriver initLocalDriver() {
        return initAndStore(new ChromeDriver(options()));
    }

    private WebDriver initAndStore(WebDriver webDriver) {
        driverList.add(webDriver);
        return webDriver;
    }

    private ChromeOptions options() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.setCapability("enableVNC", true);
        chromeOptions.setCapability("enableVideo", true);
        return chromeOptions;
    }

}
