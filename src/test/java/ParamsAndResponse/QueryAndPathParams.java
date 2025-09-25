package ParamsAndResponse;

import org.testng.annotations.Test;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;



public class QueryAndPathParams {

    @Test
    public void queryPathParams() {

        given()
                .pathParams("Path","users")
                .queryParam("page","2")
                //.queryParam("id","8")
                .when()
                .get("https://reqres.in/api/{Path}")

                .then()
                .statusCode(200)
                .log().all();
    }
    
}
