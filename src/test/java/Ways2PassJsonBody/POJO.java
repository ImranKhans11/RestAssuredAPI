package Ways2PassJsonBody;

import org.testng.annotations.Test;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class POJO {

    String id;

    @Test(priority = 1)
    public void POJORequest() {

        POJO_Getters_Setters POJO = new POJO_Getters_Setters();

        POJO.setName("Mohd");
        POJO.setCode("202210308");
        POJO.setCourse("Playwright");

        String[] gameArr = {"Chess", "Carrom"};
        POJO.setGame(gameArr);

        id = given()
                .when()
                .post("http://localhost:3000/Students")
                .jsonPath().getString("id");
    }

    @Test(priority = 2, dependsOnMethods = {"POJORequest"})
    public void deleteUser() {
        given()
                .when()
                .delete("http://localhost:3000/Students" + id);
    }
}
