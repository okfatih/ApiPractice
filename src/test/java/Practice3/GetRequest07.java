package Practice3;

import BaseUrl.GmiBankBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetRequest07 extends GmiBankBaseUrl {
    @Test
    public void test01() {
        spec.pathParams("first","tp-customers","second",110472);
        Response response = given()
                .spec(spec)
                .headers("Authorization","Bearer " + generateToken())
                .when()
                .get("/{first}/{second}");
        response.prettyPrint();

        //Matcher ile doğrula
       response.then()
                .assertThat()
                .body("firstName",equalTo("Melva"),
                        "lastName",equalTo("Bernhard"),
                        "country.name",equalTo("San Jose"));


        //JsonPath ile doğrula
        JsonPath json = response.jsonPath();
        Assert.assertEquals("Melva",json.getString("firstName"));
        Assert.assertEquals("Bernhard",json.getString("lastName"));
        Assert.assertEquals("San Jose",json.getString("country.name"));
        Assert.assertEquals("delilah.metz",json.getString("user.login"));
    }
}
/*
http://www.gmibank.com/api/tp-customers/110472 adresindeki müşteri bilgilerini doğrulayın
   “firstName”: “Melva”,
   “lastName”: “Bernhard”,
   “email”: “chas.kuhlman@yahoo.com”
   “zipCode”: “40207"
   “country” “name”: “San Jose”
   “login”: “delilah.metz”
 */