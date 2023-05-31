import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostApiTest {

@Test
    public void apiPut() {
    given().
            baseUri("https://reqres.in/api").
            when().
                get("users?page=2").
            then().
                assertThat().
                    statusCode(200).
            log().all();
}
@Test
public void getUserName() {
    given().
            baseUri("https://reqres.in/api").
            when().
                get("users?page=2").
            then().
                body("data[0].first_name", equalTo("Michael"));
}




}
