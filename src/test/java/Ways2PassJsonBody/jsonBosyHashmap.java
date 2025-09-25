package Ways2PassJsonBody;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.nio.charset.Charset;
import java.util.HashMap;

public class jsonBosyHashmap {

    String id;

    @Test(priority = 1)
    public void hashMap() {

        HashMap data = new HashMap();

        data.put("name", "Khan");
        data.put("code", "202210304");
        data.put("Course", "Mobile");

        String game[] = {"TT", "Carrom"};
        data.put("games", game);

        id = given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("http://localhost:3000/Students")
                .jsonPath().getString("id");
    }

    @Test(priority = 2, dependsOnMethods = {"hashMap"})
    void deleteUser() {
        given()

                .when()
                .delete("http://localhost:3000/Students/" + id)

                .then()
                .statusCode(200);
    }
}
