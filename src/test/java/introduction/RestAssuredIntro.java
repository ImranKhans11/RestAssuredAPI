package introduction;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class RestAssuredIntro {

    int id;

    @Test(priority = 1)
    void getUsers() {

        given()
                .contentType(ContentType.JSON)
                .pathParams("myPath", "users")
                .queryParam("page",2);

                when()
                .get("https://reqres.in/api/{mypath}")

                .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .log().all();

    }

    @Test(priority = 2)
    void createUser() {
        HashMap<String, String> data = new HashMap<>();
        data.put("name", "Tiger");
        data.put("job", "Bewarse");

        id = given()
                .header("x-api-key","reqres-free-v1")
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");


        System.out.println(id);

    }

    @Test(priority = 3, dependsOnMethods = {"createUser"})
    void updateUser() {
        HashMap data = new HashMap();
        data.put("name", "Tiger");
        data.put("job", "Tester");

        given()
                .header("x-api-key","reqres-free-v1")
                .contentType("application/json")
                .body(data)

                .when()
                .put("https://reqres.in/api/users/" + id)

                .then()
                .statusCode(200)
                .body("job", equalTo("Tester"))
                .log().all();

    }

    @Test(priority = 4,dependsOnMethods = {"updateUser"})
    void deleteUser() {
        given()
                .header("x-api-key","reqres-free-v1")
                .when()
                .delete("https://reqres.in/api/users/" + id)

                .then()
                .statusCode(204);
    }

}
