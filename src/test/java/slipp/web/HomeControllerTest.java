package slipp.web;


import static io.restassured.RestAssured.given;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import slipp.base.BaseIntegrationTest;

@Slf4j
public class HomeControllerTest extends BaseIntegrationTest {
    @Before
    public void setup() {
        log.debug("local server port : {}", serverPort);
        RestAssured.port = serverPort;
    }

    @Test
    public void home() throws Exception {
        String body = given()
        .when()
            .get("/")
        .then()
            .statusCode(HttpStatus.OK.value())
                .extract().asString();
        log.debug("body : {}", body);
    }
}
