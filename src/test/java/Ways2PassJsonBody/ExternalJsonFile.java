package Ways2PassJsonBody;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ExternalJsonFile {

    @Test
    public void externalJsonFile() throws FileNotFoundException {
        File file = new File("C:\\Users\\Imranullah\\IdeaProjects\\RestAssured\\data.json");
        FileReader fileReader = new FileReader(file);
        JSONTokener jt = new JSONTokener(fileReader);
        JSONObject jsonObject = new JSONObject(jt);

        given()
                .contentType("application/json")
                .body(jsonObject.toString())

                .when()
                .post("http://localhost:3000/Students")

                .then()
                .statusCode(201)
                .header("content-type","application/json")
                .body("Name", equalTo("Mohd"))
                .body("Id",equalTo(202210301))
                .body("Course",equalTo("Java"))
                .body("Games[0]",equalTo("TT"))
                .body("Games[1]", equalTo("Cricket"))
                .log().all();

    }
}
