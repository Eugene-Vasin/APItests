
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class APITests {
    private final String URL = "https://reqres.in/api/";


    @Test
    public void checkAvatar() {
        Specification.installSpec(Specification.requestSpec(URL), Specification.responseSpecification200OK());
        List<UserData> user = given()
                .when()
                .get("users?page=2")
                .then()
                .extract().body().jsonPath().getList("data", UserData.class);

        user.forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));

        Assert.assertTrue(user.stream().allMatch(x -> x.getEmail().endsWith("reqres.in")));
    }

    @Test
    public void postSuccess() {
        Specification.installSpec(Specification.requestSpec(URL), Specification.responseSpecification200OK());

        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";

        User user = new User("eve.holt@reqres.in", "pistol");
        SuccessResponse successResponse = given()
                .body(user)
                .when()
                .post("register")
                .then()
                .extract().as(SuccessResponse.class);
        Assert.assertNotNull(successResponse.getToken());

        Assert.assertEquals(successResponse.getId(), id);
        Assert.assertEquals(successResponse.getToken(), token);
    }

    @Test
    public void unSuccessRegTest() {
        Specification.installSpec(Specification.requestSpec(URL), Specification.responseSpecificationError400());

        User user = new User("sydney@fife", "");
        UnsuccesfulReg unsuccesfulReg = given()
        .body(user)
        .post("register")
        .then()
        .extract().as(UnsuccesfulReg.class);
        Assert.assertEquals(unsuccesfulReg.getError(), "Missing password");
    }

    @Test
    public void sortedTest() {
        Specification.installSpec(Specification.requestSpec(URL), Specification.responseSpecification200OK());
        List<ListData> listData= given()
                .when()
                .get("unknown")
                .then()
                .extract().body().jsonPath().getList("data", ListData.class);
        List<Integer> yearsList = listData.stream().map(ListData::getYear).toList();
        List<Integer> sortedYearsList = yearsList.stream().sorted().toList();

        Assert.assertEquals(yearsList, sortedYearsList);
    }


}
