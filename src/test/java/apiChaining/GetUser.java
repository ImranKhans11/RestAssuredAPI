package apiChaining;

import io.restassured.http.ContentType;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class GetUser {

    @Test
    public void getUser(ITestContext context) {

        //int id = 8136992;
        int id = (Integer)context.getSuite().getAttribute("userId");
        String BearerToken = (String)context.getSuite().getAttribute("BearerToken");

        given()
                .header("Authorization", "Bearer " + BearerToken)
                .contentType(ContentType.JSON)
                .pathParams("id", id)

                .when()
                .get("https://gorest.co.in/public/v2/users/" + "{id}")

                .then()
                .statusCode(200);
    }
}
