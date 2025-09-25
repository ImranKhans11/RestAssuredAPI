package apiChaining;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateUser {

    @Test
    public void createUser(ITestContext context) {

        Faker faker = new Faker();

        JSONObject data = new JSONObject();

        data.put("name", faker.name().fullName());
        data.put("email", faker.internet().emailAddress());
        data.put("gender", "male");
        data.put("status", "Inactive");

        String token = "c74cc8421866b54c675c82c11fa9dc7d0f9cf64cefb2fdc0efca4d65ca5a4ece";

        int id = given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .body(data.toString())

                .when()
                .post("https://gorest.co.in/public/v2/users")
                .jsonPath().getInt("id");

        context.getSuite().setAttribute("userId", id);
        context.getSuite().setAttribute("BearerToken", token);
    }
}
