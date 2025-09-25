package apiChaining;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class UpdateUser {

    @Test
    public void updateUser(ITestContext context) {

        int id = (Integer)context.getSuite().getAttribute("userId");
        String BearerToken = (String)context.getSuite().getAttribute("BearerToken");

        Faker faker = new Faker();

        JSONObject data = new JSONObject();
        data.put("name", faker.name().fullName());
        data.put("email", faker.internet().emailAddress());
        data.put("gender", "male");
        data.put("status", "active");

        given()
                .header("Authorization", "Bearer " + BearerToken)
                .contentType(ContentType.JSON)
                .pathParams("id", id)

                .when()
                .put("https://gorest.co.in/public/v2/users/{id}")

                .then()
                .log().all();
    }

}
