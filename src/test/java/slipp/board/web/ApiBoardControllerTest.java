package slipp.board.web;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import slipp.base.BaseIntegrationTest;

public class ApiBoardControllerTest extends BaseIntegrationTest {
    @Test
    public void create_로그인사용자() throws Exception {
        Map<String, String> board = new HashMap<>();
        board.put("name", "나만의 보드");
        
        given_auth_json()
                .body(board)
            .when()
                .post("/api/boards")
            .then()
                .statusCode(HttpStatus.CREATED.value());
    }
    
    @Test
    public void create_로그아웃사용자() throws Exception {
        Map<String, String> board = new HashMap<>();
        board.put("name", "나만의 보드");
        
        given()
                .body(board)
            .when()
                .post("/api/boards")
            .then()
                .statusCode(HttpStatus.FOUND.value());
    }
    
    @Test
    public void delete_글쓴이() throws Exception {
        create_로그인사용자();
        given_auth_json()
            .when()
                .delete("/api/boards/1")
            .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }
    
    @Test
    public void delete_다른사용자() throws Exception {
        create_로그인사용자();
        given_auth_json("sanjigi@slipp.net")
            .when()
                .delete("/api/boards/1")
            .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }
}