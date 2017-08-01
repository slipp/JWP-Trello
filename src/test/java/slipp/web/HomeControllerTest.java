package slipp.web;


import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import slipp.base.BaseIntegrationTest;

import static io.restassured.RestAssured.given;

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
