package Ways2PassJsonBody;

import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class JsonLibrary {

    String id;

    @Test
    void jsonLibrary() {

        JSONObject data = new JSONObject();

        data.put("name", "Khan");
        data.put("code", "202210304");
        data.put("Course", "Mobile");

        String game[] = {"TT", "Carrom"};
        data.put("games", game);

        id = given()
                .contentType("application/json")
                .body(data.toString())

                .when()
                .post("http://localhost:3000/Students")
                .jsonPath().getString("id");

    }

    @Test(dependsOnMethods = {"jsonLibrary"})
    void deleteUser () {

        given()

                .when()
                .delete("http://localhost:3000/Students/" + id)

                .then()
                .statusCode(200);
    }
}
