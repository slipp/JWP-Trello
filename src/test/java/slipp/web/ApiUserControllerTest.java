package slipp.web;

import static io.restassured.RestAssured.given;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import slipp.base.BaseIntegrationTest;
import slipp.user.User;

@Slf4j
public class ApiUserControllerTest extends BaseIntegrationTest {
    @Before
    public void setup() {
        log.debug("local server port : {}", serverPort);
        RestAssured.port = serverPort;
    }

    @Test
    public void create() throws Exception {
        User user = new User("userId", "password", "javajigi@slipp.net");
        String body =
                given()
                    .contentType(ContentType.JSON)
                    .body(user)
                .when()
                    .post("/api/users")
                .then()
                    .statusCode(HttpStatus.CREATED.value())
                        .extract().asString();

        log.debug("body : {}", body);
    }
}
