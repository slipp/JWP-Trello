package slipp.board.web;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import slipp.base.BaseIntegrationTest;

public class ApiBoardControllerTest extends BaseIntegrationTest {
    @Test
    public void create() throws Exception {
        Map<String, String> board = new HashMap<>();
        board.put("name", "나만의 보드");
        
        given_auth_json()
                .body(board)
            .when()
                .post("/api/boards")
            .then()
                .statusCode(HttpStatus.CREATED.value());
    }
}