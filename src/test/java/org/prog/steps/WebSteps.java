package org.prog.steps;

import io.cucumber.java.en.Given;
import org.pages.GooglePage;
import org.prog.api.dto.UserDto;
import org.prog.util.DataHolder;

public class WebSteps {
    public static GooglePage googlePage = new GooglePage();

    @Given("Load google page")
    public void loadPage() {
        googlePage.loadPage();
        googlePage.acceptCookies();
    }

    @Given("Search for user {string}")
    public void searchForActiveUser(String alias) {
        UserDto user = (UserDto) DataHolder.getInstance().get(alias);
        googlePage.appendSearchText(user.getName().getFirst());
        googlePage.appendSearchText(" ");
        googlePage.appendSearchText(user.getName().getLast());
        googlePage.performSearch();
    }
}
