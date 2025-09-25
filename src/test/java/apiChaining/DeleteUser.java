package apiChaining;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matcher.*;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteUser {

    @Test
    public void deleteUser(ITestContext context) {

        int id = (Integer)context.getSuite().getAttribute("userId");
        String BearerToken = (String)context.getSuite().getAttribute("BearerToken");

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + BearerToken)
                .pathParams("id", id)

                .when()
                .delete("https://gorest.co.in/public/v2/users/" + "{id}")

                .then()
                .statusCode(204);
    }
}
