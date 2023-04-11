package org.prog.steps;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.prog.api.dto.SearchResultsDto;

public class RestSteps {

  private final static String URL =
      "https://randomuser.me/api/?inc=gender,name,nat&noinfo&results=%s";

  public static SearchResultsDto searchResultsDto;

  @Given("i generate {int} random person")
  public void generateRandomPerson(int amount) {
    RequestSpecification rs = RestAssured.given();
    Response response = rs.get(getUsers(amount));
    response.then().assertThat().statusCode(200);

    searchResultsDto = response.as(SearchResultsDto.class);
  }

  private String getUsers(int amount) {
    return String.format(URL, amount);
  }


}
