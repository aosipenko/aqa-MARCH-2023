package org.prog.cucumber;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestRunFinished;
import io.cucumber.plugin.event.TestRunStarted;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.prog.util.DriverName;
import org.prog.util.WebDriverFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CucumberHooks implements EventListener {

    @Autowired
    private WebDriverFactory webDriverFactory;

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
        WebDriverFactory.getInstance().quitAllDriver();
    }

    @SneakyThrows
    public static WebDriver getDriver() {
        return WebDriverFactory.getInstance()
                .getDriver(DriverName.byName(System.getenv("driver.name")));
    }
}
