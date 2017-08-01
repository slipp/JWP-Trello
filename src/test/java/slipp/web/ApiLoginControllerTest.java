package slipp.web;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import slipp.base.BaseIntegrationTest;
import slipp.domain.Authentication;

import static io.restassured.RestAssured.given;

@Slf4j
public class ApiLoginControllerTest extends BaseIntegrationTest {
    @Before
    public void setup() {
        log.debug("local server port : {}", serverPort);
        RestAssured.port = serverPort;
    }

    @Test
    public void login_success() throws Exception {
        Authentication authentication = new Authentication("javajigi", "test");
        given()
                .contentType(ContentType.JSON)
                .body(authentication)
                .when()
                    .post("/api/login")
                .then()
                    .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void login_failure_when_user_not_found() throws Exception {
        Authentication authentication = new Authentication("sanjigi", "test");
        String body = given()
                .contentType(ContentType.JSON)
                .body(authentication)
                .when()
                .post("/api/login")
                .then()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .extract().asString();

        log.debug("body : {}", body);
    }

    @Test
    public void login_failure_when_mis_match_password() throws Exception {
        Authentication authentication = new Authentication("javajigi", "test2");
        String body = given()
                .contentType(ContentType.JSON)
                .body(authentication)
                .when()
                .post("/api/login")
                .then()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .extract().asString();

        log.debug("body : {}", body);
    }
}
