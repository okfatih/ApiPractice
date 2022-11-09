package Practice3;

import BaseUrl.GmiBankBaseUrl;
import POJOS.Account;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GmiAccountPost extends GmiBankBaseUrl {
    @Test
    public void postGmi() {
        spec.pathParams("first","tp-accounts");
        Account payload = new Account(124567,"Fatih's account",20,"DebitCard","Closed","02-12-2021","02-12-2022","null",null);
        Response response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .body(payload)
                .post("/{first}");
        JsonPath jsonPath = response.jsonPath();
        System.out.println("payload = " + payload);
        response.prettyPrint();


    }
}


/*
{
    "id": 128488,
    "description": "MyAccount002",
    "balance": 550,
    "accountType": "CREDIT_CARD",
    "accountStatusType": "ACTIVE",
    "createDate": "0020-12-11T22:04:08Z",
    "closedDate": "0022-12-09T22:04:08Z",
    "employee": null,
    "accountlogs": null
}
 */