import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get01 {
    @Test
    public void test01() {
        String url = "https://restful-booker.herokuapp.com/booking";
        Response response = given().when().get(url);
      response.prettyPrint();
        // endpointe göndermek için request oluşturmuş olduk
        //response.prettyPeek(); //Response daki her şeyi yazdırır
       // response.pe ek();
     //   response.print();
        System.out.println(response.contentType());
        System.out.println(response.statusLine());
        System.out.println(response.contentType());

        // Junit assertleri ile api testi yapabiliriz
        assertEquals("status code hatalı",200,response.getStatusCode());
        assertEquals("HTTP/1.1 200 OK",response.statusLine());

        // Assertthat ile
        response.then().assertThat()
                .statusCode(200);

    }
}
