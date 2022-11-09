package BaseUrl;

import utilities.Authentication;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import utilities.Authentication;

public class GmiBankBaseUrl extends Authentication {
    protected RequestSpecification spec;
    @Before
    public void setUp(){
        spec = new RequestSpecBuilder().setBaseUri("http://www.gmibank.com/api").build();
    }
}
