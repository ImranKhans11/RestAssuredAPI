package parsingJSONObject;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class ParsingJSONObject {

    @Test
    public void parsingJSONObject() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/Employee")


                .then()
                .statusCode(200)
                .body("[2].Course", equalTo("Playwright"));
    }

    @Test
    public void Assertion1() {
        Response response = given()
                .when()
                .get("http://localhost:3000/Employee");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.header("Content-Type"),"application/json");

        String course = response.jsonPath().get("[2].Course").toString();

        Assert.assertEquals(course,"Playwright");
    }

    @Test
    public void Assertion2() {
        Response response = given()
                .when().get("http://localhost:3000/Employee");

        JSONObject jsonObject = new JSONObject(response.asString());

        boolean status = false;

        for (int i=0; i<jsonObject.getJSONArray("data").length(); i++) {
            String coursesNames = jsonObject.getJSONArray("data").getJSONObject(i).get("Course").toString();

            if(coursesNames.equals("Playwright")) {

                status = true;
                break;
            }
        }
        Assert.assertEquals(status,true);
        System.out.println(status);
    }

    @Test
    public void Assertion3() {
        Response response = given()
                .when().get("http://localhost:3000/Employee");

        JSONObject jsonObject = new JSONObject(response.asString());
        double FinalCodeValue = 0;
        for (int i=0; i<jsonObject.getJSONArray("data").length(); i++ ) {
            String getValue = jsonObject.getJSONArray("data").getJSONObject(i).get("Code").toString();
            FinalCodeValue = FinalCodeValue + Double.parseDouble(getValue);
        }
        System.out.println(FinalCodeValue);

        Assert.assertEquals(FinalCodeValue, 80884.121, 0.001);;
    }
}
