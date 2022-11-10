package Practice4;

import BaseUrl.GmiBankBaseUrl;
import POJOS.Customer;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import utilities.ObjectMapperUtils;
import utilities.ReadText;
import utilities.WriteToText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GMIBank01 extends GmiBankBaseUrl {
    @Test
    public void test01() throws IOException {
        spec.pathParams("first", "tp-customers");
        Response response = given()
                .spec(spec)
                .headers("Authorization", "Bearer " + generateToken())
                .when()
                .get("/{first}");
     //response.prettyPrint();
        Customer[] customers;
        //Tüm customer bilgilerini ekrana yazdırın
        customers = ObjectMapperUtils.convertJsontoJava(response.asString(),Customer[].class);
        for (int i =0; i<customers.length; i++){

            System.out.println(customers[i]);
        }
        //Tüm ssnleri yazdırın
        for (int i = 0; i <customers.length ; i++) {
            System.out.println(customers[i].getSsn());

        }
        //Tüm customer ssnleri text olarak kaydedin
        String fileName = "src/test/java/Practice4/SSNNumbers.txt";
        WriteToText.saveSSNData(fileName,customers);

        //Oluşturduğunuz text dosyyasından SSNleri okuyorak 531-95-8437, 049-43-2360, 123-34-3434 SSN lerini doğrulayın
        List<String>expectedSSNdata = new ArrayList<>();
       expectedSSNdata.add("531-95-8437");
       expectedSSNdata.add("049-43-2360");
       expectedSSNdata.add("123-34-3434");

        List<String>actualSSN = ReadText.readCustomerSSNList(fileName);
        Assert.assertTrue("No Match in SSN",actualSSN.containsAll(expectedSSNdata));



    }

}
/*
tp-customers end pointine request gönder
Tum Customer bilgilerini ekrana yazdır
TÜM Customer ssn lerini ekrana yazdır
 */