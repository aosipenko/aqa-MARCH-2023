package org.prog.steps;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.prog.api.dto.SearchResultsDto;
import org.prog.util.DataHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class RestSteps {

  private final static String URL =
      "https://randomuser.me/api/?inc=gender,name,nat&noinfo&results=%s";

  @Autowired
  private DataHolder dataHolder;

  @Given("i generate {int} random person as {string}")
  public void generateRandomPerson(int amount, String alias) {
    RequestSpecification rs = RestAssured.given();
    Response response = rs.get(getUsers(amount));
    response.then().assertThat().statusCode(200);

    dataHolder.put(alias, response.as(SearchResultsDto.class));
  }

  private String getUsers(int amount) {
    return String.format(URL, amount);
  }


}
