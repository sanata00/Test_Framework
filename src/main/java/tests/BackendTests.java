package tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class BackendTests {

    @Test
    public void getUsers() {

        when().get("https://reqres.in/api/users").then().statusCode(200);

    }
}
