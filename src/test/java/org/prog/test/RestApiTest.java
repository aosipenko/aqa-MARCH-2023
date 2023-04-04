package org.prog.test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.prog.api.dto.SearchResultsDto;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RestApiTest {

    private final static String URL =
            "https://randomuser.me/api/?inc=gender,name,nat&noinfo&results=%s";

    @Test
    public void myApiTest() {
        RequestSpecification rs = RestAssured.given();
        Response response = rs.get(getUsers(10));
        ValidatableResponse vr = response.then();

        vr.assertThat().statusCode(200);
//        vr.assertThat().body("results.gender[1]",
//                Matchers.in(List.of("male", "female")));

        SearchResultsDto searchResultsDto = response.as(SearchResultsDto.class);
        Assert.assertTrue(searchResultsDto.getResults().stream()
                .anyMatch(userDto -> userDto.getGender().equals("male")));
    }

    @Test
    public void apacheClientTest() throws IOException, ParseException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(getUsers(10));
        CloseableHttpResponse response = client.execute(get);
        String json = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        System.out.println(json);
    }


    public void postSample() {
        RestAssured.given()
                .body("test")
                .accept(ContentType.JSON)
                .post(URL)
                .then()
                .assertThat()
                .statusCode(200);

        RequestSpecification rs = RestAssured.given();
        SearchResultsDto dto = new SearchResultsDto();
        rs.body(dto);
        rs.contentType(ContentType.JSON);
        rs.accept(ContentType.HTML);
        Response response = rs.post();
    }

    private String getUsers(int amount) {
        return String.format(URL, amount);
    }
}
