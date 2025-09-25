package cookiesAndHeaders;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class CookiesAndHeaders {

   // @Test(priority = 1)
    public void cookies() {
        given()
                .when()
                .get("https://www.google.com/")

                .then()
                .cookie("AEC","AVh_V2gXqTO62aJHhizoqTSFJjEcd1DVjmL9ZdG5JepjdwmvgO7b5zCRuSY")

                .log().all();
    }

    //@Test(priority = 2)
    public void cookiePrint() {
        Response res = given()
                                .when()
                                .get("https://www.google.com/");

            String cookie = res.getCookie("AEC");
            System.out.println(cookie);

        Map<String, String> cookies = res.getCookies();
        System.out.println(cookies.keySet());

        for (String K : cookies.keySet()) {
            String cookieValue = res.getCookie(K);
            System.out.println(K + "  " + cookieValue);
        }
    }

    @Test(priority = 3)
    public void header() {

        given()
                .when()
                .get("https://www.google.com/")

                .then()
                .statusCode(200)
                .header("Content-Type", "text/html; charset=ISO-8859-1")
                .and()
                .header("Server", "gws")
                .header("Cache-Control", "private, max-age=0");
    }

    @Test(priority = 4)
    public void getHeader() {

        Response response = given()
                .when()
                .get("https://www.google.com/");

        String header = response.getHeader("Content-Type");
        System.out.println(header);
    }

    @Test(priority = 5)
    public void headers() {

        Response response = given()
                .when()
                .get("https://www.google.com/");

        Headers headers = response.getHeaders();

        for (Header hd : headers) {

            System.out.println(hd.getName() + "   " + hd.getValue());
        }

    }


}
