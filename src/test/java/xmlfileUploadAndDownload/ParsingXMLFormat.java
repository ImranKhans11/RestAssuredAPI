package xmlfileUploadAndDownload;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.List;

public class ParsingXMLFormat {

    @Test
    public void parsingXML() {

        RequestSpecification requestSpecification = RestAssured.given();

        requestSpecification.baseUri("https://www.w3schools.com");
        requestSpecification.basePath("/xml/simple.xml");
        //requestSpecification.basePath("data");
        requestSpecification.header("accept", "application/xml");

        Response response = requestSpecification.get();
        response.then().header("Content-Type","text/xml");
        response.then().statusCode(200);
        String foodName = response.body().xmlPath().getString("breakfast_menu.food[0].name");
        System.out.println(foodName);

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(foodName,"Belgian Waffles");

    }

    @Test
    public void xmlPath() {

        RequestSpecification requestSpecification1 = RestAssured.given();

        Response xmlResponse = requestSpecification1.when().get("https://www.w3schools.com/xml/simple.xml");

        XmlPath xmlPath = new XmlPath(xmlResponse.asString());

        List<String> listOfFood = xmlPath.getList("breakfast_menu.food.name");
        Assert.assertEquals(listOfFood.size(),5);
        boolean Status = false;
        for(String food:listOfFood) {
            if (food.equals("Homestyle Breakfast")){
                Status = true;
                break;
            }
        }
        System.out.println(Status);
    }
}
