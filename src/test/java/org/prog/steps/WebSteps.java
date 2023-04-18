package org.prog.steps;

import io.cucumber.java.en.Given;
import org.pages.GooglePage;

public class WebSteps {
    public static GooglePage googlePage = new GooglePage();

    @Given("Load google page")
    public void loadPage() {
        googlePage.loadPage();
        googlePage.acceptCookies();
    }

    @Given("Search for active user")
    public void searchForActiveUser() {
        googlePage.appendSearchText(SqlSteps.activeUser.getName().getFirst());
        googlePage.appendSearchText(" ");
        googlePage.appendSearchText(SqlSteps.activeUser.getName().getLast());
        googlePage.performSearch();
    }
}
