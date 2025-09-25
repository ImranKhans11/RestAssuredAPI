package authentications;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Authentications {

    @Test(priority = 1)
    public void basicAuth() {
        given()
                .auth().basic("postman", "password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();
    }

    @Test(priority = 2)
    public void digestAuth() {
        given()
                .auth().digest("postman", "password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();
    }

    @Test(priority = 3)
    public void preemptiveAuth() {
        given()
                .auth().preemptive().basic("postman", "password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();
    }

    public void oauth1() {
        given()
                .auth().oauth("consumerKey", "consumerSecret", "accessToken", "accessSecret")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 4)
    public void bearerToken() {
        // Token should be set as environment variable: GITHUB_PAT
        String token = System.getenv("GITHUB_PAT");

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(200)
                .log().all();
    }

    public void oAuth2() {
        // Example placeholder OAuth2 token
        String accessToken = System.getenv("OAUTH2_TOKEN");

        given()
                .auth().oauth2(accessToken)
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 6)
    public void apiKey() {
        // API key should be set as environment variable: OPENWEATHER_API_KEY
        String apiKey = System.getenv("OPENWEATHER_API_KEY");

        given()
                .pathParams("path", "geo/1.0/direct")
                .queryParam("q", "london")
                .queryParam("limit", "5")
                .queryParam("appid", apiKey)
                .when()
                .get("http://api.openweathermap.org/{path}")
                .then()
                .statusCode(200)
                .log().all();
    }
}
