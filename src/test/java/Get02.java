import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get02 {
    @Test
    public void get02() {
        String url = "https://reqres.in/api/users";

       Response response =  given().when().get(url);
        System.out.println(response.statusLine());
        System.out.println(response.contentType());

        response.prettyPrint();


       //Header Test
        response.then().assertThat().statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .contentType(ContentType.JSON);
       //Matchers kullanarak datalara ulaşma
    }
}
