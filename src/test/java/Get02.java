import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Get02 {
    @Test
    public void get02() {
        String url = "https://reqres.in/api/users";

        Response response = given().when().get(url);
        System.out.println(response.statusLine());
        System.out.println(response.contentType());

        response.prettyPrint();


        //Header Test
        response.then().assertThat().statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .contentType(ContentType.JSON);


        //Body Test
        /*
        idsi 1 olanın datalarını aşağıdaki gibi oldugunu
        test edin
         "email": "george.bluth@reqres.in",
            "first_name": "George",
            "last_name": "Bluth",
            "avatar": "https://reqres.in/img/faces/1-image.jpg"
         */
        //Matchers kullanarak datalara ulaşma
        response.then().body("data[0].email", equalTo("george.bluth@reqres.in"),
                "data[0].first_name", equalTo("George"),
                "data[0].last_name", equalTo("Bluth"));
    }
}
/*
 API DERSİNDE NELER ÖĞRENECEKSİNİZ?

* POSTMAN

* Rest Assured: REST API'lerini test etmek ve doğrulamak için kullanılan
                Open Source (Açık Kaynak) bir Java kütüphanesidir.

* Matchers Class kullanarak doğrulama
  http://hamcrest.org/JavaHamcrest/javadoc/1.3/org/hamcrest/Matchers.html

  Matchers.equalto(): Key-Value şeklinde girilen datanın, eşit olduğunu doğrulamak için kullanılır.
  Matchers.hasSize(): Datanın size'ını doğrulamak için kullanılır.
  Matchers.hasItem(): Girilen tek bir data'yı doğrulamak için kullanılır.
  Matchers.hasItems(): Girilen birden fazla datayı doğrulamak için kullanılır.

* JUnit ve TestNG Assert'leri kullanılarak doğrulama

1* Json Path kullanarak doğrulama
    JsonPath() json = response.JsonPath();

2* De-Serialization: JSON formatını Javaya dönüştürme
    HashMap<String, Object> actualData = response.as(HashMap.class);

3* Serialization: Java yapısında olan dataları JSON'a dönüştürme
    Gson gson = new Gson();
    String jsonFromJava = gson.toJson(actual);

4* JSON Object
    JsonPlaceHolderTestData testObje = new JsonPlaceHolderTestData();
    JSONObject expectedRequest = testObje.setUpPostData();

5* Pojo Class (Plain Old Java Object)

6* ObjectMapper: JSON ve POJO (Plain Old Java Objects) okuma ve yazma işlevlerinin yanı sıra
    dönüştürmeleri gerçekleştirmek için de kullanılır.

7* API den alınan response'ları bilgisayara text dosyası olarak kaydetme ve doğrulama yapma.
 */