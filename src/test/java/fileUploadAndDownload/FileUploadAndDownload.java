package fileUploadAndDownload;

import static io.restassured.RestAssured.*;
import static io.restassured.response.Response.*;
import static io.restassured.specification.RequestSpecification.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileUploadAndDownload {

    @Test
    public void fileUpload() throws IOException {
        File fileUpload = new File("C:\\Users\\Imranullah\\Desktop\\API_practice\\StudentData.json");

        String fileContent = new String(Files.readAllBytes(fileUpload.toPath()));
        System.out.println("📄 File Content:");
        System.out.println(fileContent);

        given()

                .multiPart("file", fileUpload)
                .contentType("multipart/form-data")

                .when()
                .post("https://the-internet.herokuapp.com/upload")

                .then()
                .statusCode(200)
                .header("Content-Type", "text/html;charset=utf-8");

    }

    @Test
    public void downloadFile() {
        given()
                .when()
                //Not working as we do not have download option on this site
                .get("https://the-internet.herokuapp.com/download")
                .then()
                .statusCode(200)
                .log().body();
    }
}
