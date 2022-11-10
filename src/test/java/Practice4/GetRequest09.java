package Practice3;

import BaseUrl.GmiBankBaseUrl;
import POJOS.Country;
import POJOS.Customer;
import POJOS.User;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.oauth;
import static org.junit.Assert.assertEquals;

public class GetRequest09 extends GmiBankBaseUrl {
    @Test
    public void test09() {
        spec.pathParams("first","tp-customers","second",110452);
        Response response = given()
                .spec(spec)
                .headers("Authorization", "Bearer " + generateToken())
                .when()
                .get("/{first}/{second}");
        response.prettyPrint();
        User user = new User(110016,"leopoldo.reinger", "Jasmine", "Stehr",
                "marni.zboncak@yahoo.com", true, "en", null, null);
        System.out.println(user);
        Country country = new Country(3, "USA", null);
        System.out.println("country = " + country);
        Customer expectedData = new Customer(110452, "Jasmine", "Stehr", "V", "marni.zboncak@yahoo.com"
                , "463-609-2097", "1-112-497-0270", "16525", "14387 Al Ridge5343 Bert Burgs","Waltermouth"
                , "761-59-2911", "2021-11-28T21:00:00Z", false, country, "California", user);

        Customer actualData = response.as(Customer.class);
        assertEquals(expectedData.getUser().getEmail(),actualData.getUser().getEmail());
        assertEquals(expectedData.getCity(),actualData.getCity());
        assertEquals(expectedData.getCountry().getName(),actualData.getCountry().getName());
        System.out.println("******************");
        Customer actualData2 = ObjectMapperUtils.convertJsontoJava(response.asString(),Customer.class);
        System.out.println("actualData2: " + actualData2);
        assertEquals(expectedData.getFirstName(),actualData2.getFirstName());
        assertEquals(expectedData.getCountry().getName(),actualData2.getCountry().getName());
    }
}
/*
/*
http://www.gmibank.com/api/tp-customers/110452
"user": {
        "id": 110016,
        "login": "leopoldo.reinger",
        "firstName": "Jasmine",
        "lastName": "Stehr",
        "email": "marni.zboncak@yahoo.com",
        "activated": true,
        "langKey": "en",
        "imageUrl": null,
        "resetDate": null
 */

