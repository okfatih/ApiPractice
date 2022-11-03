package Day02;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest04 {
    @Test
    public void test04() {
        String url = "http://dummy.restapiexample.com/api/v1/employees";
        Response response = given().when().get(url);
        response.prettyPrint();
        response.then().assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200);
        response.then()
                .assertThat()
                .body("data", hasSize(24),
                        "data.employee_name", hasItem("Ashton Cox"),
                        "data.employee_age",hasItems(21,61,23));
    }
}
/*
http://dummy.restapiexample.com/api/v1/employees  url’ine
GET request’i yolladigimda gelen response’un
status kodunun 200 ve content type’inin “application/json”
ve employees sayisinin 24
ve employee’lerden birinin “Ashton Cox”
ve gelen yaslar icinde 21, 61, ve 23 degerlerinden birinin oldugunu test edin.
 */