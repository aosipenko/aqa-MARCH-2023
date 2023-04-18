package org.prog.cucumber;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestRunFinished;
import io.cucumber.plugin.event.TestRunStarted;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.pages.GooglePage;
import org.prog.steps.WebSteps;

import java.net.InetAddress;
import java.net.URL;
import java.util.Objects;

public class CucumberHooks implements EventListener {

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestRunStarted.class, this::setUp);
        eventPublisher.registerHandlerFor(TestRunFinished.class, this::tearDown);
    }

    public void setUp(TestRunStarted event) {
//        if (Objects.isNull(WebSteps.googlePage)) {
//            WebSteps.googlePage = new GooglePage();
//        }
    }

    public void tearDown(TestRunFinished event) {
        WebSteps.googlePage.quitDriver();
    }

    @SneakyThrows
    public static WebDriver getDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.setCapability("enableVNC", true);
        chromeOptions.setCapability("enableVideo", true);

        if ("TSELSE3871808".equals(InetAddress.getLocalHost().getHostName())) {
            return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);
        } else {
            return new RemoteWebDriver(new URL("http://selenoid:4444/wd/hub"), chromeOptions);
        }
    }
}
