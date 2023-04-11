package org.prog.cucumber;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestRunFinished;
import io.cucumber.plugin.event.TestRunStarted;
import java.util.Objects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.prog.steps.WebSteps;

public class CucumberHooks implements EventListener {

  @Override
  public void setEventPublisher(EventPublisher eventPublisher) {
    eventPublisher.registerHandlerFor(TestRunStarted.class, this::setUp);
    eventPublisher.registerHandlerFor(TestRunFinished.class, this::tearDown);
  }

  public void setUp(TestRunStarted event) {
    if (Objects.isNull(WebSteps.webDriver)) {
      WebSteps.webDriver = getDriver();
    }
  }

  public void tearDown(TestRunFinished event) {
    WebSteps.webDriver.quit();
  }

  public static WebDriver getDriver() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--remote-allow-origins=*");
    return new ChromeDriver(chromeOptions);
  }


}
