package tests;

import helpers.Log;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BackendTests {

  @Test
  public void getUsers() {
    Log.info("START BackendTests.getUsers.");
    Response response = given().queryParam("page", 2)
        .when().get("https://reqres.in/api/users")
        .then().statusCode(200)
        .extract().response();

    //Just for demonstration
    assertEquals(response.getStatusCode(), 200);

    JsonPath jsonPath = response.jsonPath();
    int totalValue = jsonPath.get("total");
    Log.info("Total: " + totalValue);
    assertEquals(totalValue, 12);
    String lastName = jsonPath.get("data[0].last_name");
    Log.info("Last name: " + lastName);
    assertEquals(lastName, "Holt");

    //Get a node of value
    HashMap<String, String> map = jsonPath.get("data[0]");
    String firstName = map.get("first_name");
    Log.info("First name: " + firstName);
    assertEquals("Eve", firstName);

    Log.info("END BackendTests.getUsers.");
  }

  @Test
  public void getUser() {
    Log.info("START BackendTests.getUser.");

    Response response = when().get("https://reqres.in/api/users/1")
        .then().statusCode(200)
        .extract().response();

    JsonPath jsonPath = response.jsonPath();
    HashMap<String, Object> userData = jsonPath.get("data");
    int id = (int) userData.get("id");
    Log.info("Id: " + id);
    assertEquals(1, id);
    Log.info("END BackendTests.getUser.");
  }

  @Test
  public void getInvalidUser() {
    Log.info("START BackendTests.getInvalidUser.");
    when().get("https://reqres.in/api/users/23")
        .then().statusCode(404);
    Log.info("END BackendTests.getInvalidUser.");
  }

  @Test
  public void createUser() {
    Log.info("START BackendTests.createUser.");
    String payload = "{\n" +
        "    \"name\": \"morpheus\",\n" +
        "    \"job\": \"leader\"\n" +
        "}";

    Response response = given().contentType(ContentType.JSON).body(payload)
        .when().post("https://reqres.in/api/users")
        .then().statusCode(201)
        .extract().response();

    JsonPath jsonPath = response.jsonPath();
    assertEquals(jsonPath.get("name"), "morpheus");
    assertEquals(jsonPath.get("job"), "leader");
    assertTrue(Integer.valueOf(jsonPath.get("id").toString()) > 0);
    Log.info("END BackendTests.createUser.");
  }
}
