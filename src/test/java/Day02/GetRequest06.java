package Day02;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import utilities.Authentication;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

public class GetRequest06 extends Authentication {
    @Test
    public void get06() {
        String url = "https://www.gmibank.com/api/tp-customers/114351";
        Response response = given().headers("Authorization", "Bearer " + generateToken()).when().get(url);
        response.prettyPrint();

//        //Matcher class ile müşteri bilgierini doğrulayın
//     response.then().assertThat().body("firstName", Matchers.equalTo("Della"),
//                        "lastName", equalTo("Heaney"),
//                        "email", equalTo("ricardo.larkin@yahoo.com"),
//                        "mobilePhoneNumber", equalTo("123-456-7893"),
//                        "accounts[0].balance", equalTo("11190"),
//                        "accounts[accountStatusType]", equalTo("ACTIVE"),
//                        "accounts[1].balance", equalTo(69700),
//                        "accounts[1].accountType",equalTo("CREDIT_CARD"));
        response.then().assertThat().body("firstName",equalTo("Della"),
                "lastName",equalTo("Heaney"),
                "email",equalTo("ricardo.larkin@yahoo.com"),
                "mobilePhoneNumber",equalTo("123-456-7893"),
                "accounts[0].balance",equalTo(11190),
                "accounts[0].accountType",equalTo("CHECKING"),
                "accounts[1].balance",equalTo(69700),
                "accounts[1].accountType",equalTo("CREDIT_CARD"));
        //JsonPath ile müşteri bilgilerini doğrulayınız
        JsonPath json = response.jsonPath();
        assertEquals("Della",json.getString("firstName"));
        assertEquals("Heaney",json.getString("lastName"));
        assertEquals("CHECKING",json.getString("accounts[0].accountType"));



    }
}
