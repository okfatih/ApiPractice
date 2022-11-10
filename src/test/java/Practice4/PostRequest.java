package Practice4;

import BaseUrl.GmiBankBaseUrl;
import POJOS.CountryPost;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PostRequest extends GmiBankBaseUrl {
    @Test
    public void test01() {
        spec.pathParam("first", "tp-countries");

        CountryPost payload = new CountryPost("Turkmenistan");
        System.out.println("payload = " + payload);
        Response response = given()

                .headers("Authorization", "Bearer " + generateToken(), "Content-Type",ContentType.JSON)
               .contentType(ContentType.JSON)
                .spec(spec)
                .when()
                .body(payload)
                .post("/{first}");

      response.prettyPrint();
      /*

        Response response = given()
                        .headers("Authorization", "Bearer " + generateToken(), "Content-Type", ContentType.JSON)
                        .spec(spec01).when().body(countryPost)
                        .post("/{first}");
       */
    }


}
