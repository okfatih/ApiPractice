package Practice3;

import BaseUrl.GmiBankBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequest08 extends GmiBankBaseUrl {
    @Test
    public void test01() {

        //Expected data oluştur
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstName", "Alda");
        expectedData.put("lastName", "Monahan");
        expectedData.put("middleInitial", "Nichelle Hermann Kohler");
        expectedData.put("email", "com.github.javafaker.Name@7c011174@gmail.com");
        expectedData.put("mobilePhoneNumber", "909-162-8114");
        expectedData.put("city", "St Louis");
        expectedData.put("ssn", "108-53-6655");
        System.out.println("expectedData = " + expectedData);

        //Request and Response oluştur
        spec.pathParams("first", "tp-customers", "second", 43703);
        Response response = given()
                .spec(spec)
                .headers("Authorization", "Bearer " + generateToken())
                .when()
                .get("/{first}/{second}");
        response.prettyPrint();

        Map<String, Object> actualResponse = response.as(HashMap.class);
        System.out.println("actualResponse = " + actualResponse);
        assertEquals(expectedData.get("firstName"), actualResponse.get("firstName"));
        assertEquals(expectedData.get("lastName"), actualResponse.get("lastName"));
        assertEquals(expectedData.get("middleInitial"), actualResponse.get("middleInitial"));
        assertEquals(expectedData.get("email"), actualResponse.get("email"));


    }
}
/*
http://www.gmibank.com/api/tp-customers/43703
        “firstName”: “Alda”,
          “lastName”: “Monahan”,
          “middleInitial”: “Nichelle Hermann Kohler”,
          “email”: “com.github.javafaker.Name@7c011174@gmail.com”,
          “mobilePhoneNumber”: “909-162-8114”,
          “city”: “St Louis”,
          “ssn”: “108-53-6655"
          1) MATCHERS CLASS
          2) JSON PATH
          3) De-Serialization
 */