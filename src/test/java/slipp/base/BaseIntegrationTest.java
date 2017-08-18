package slipp.base;

import static io.restassured.RestAssured.given;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import slipp.auth.domain.User;
import slipp.auth.domain.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public abstract class BaseIntegrationTest {
    @Value("${local.server.port}")
    protected int serverPort;
    
    @Autowired
    private UserRepository userRepository;
    
    protected User loginUser;
    
    @Before
    public void setup() {
        RestAssured.port = serverPort;
        loginUser = userRepository.findByEmail("javajigi@slipp.net");
    }
    
    protected RequestSpecification given_auth_json() {
        return given()
                    .auth().preemptive().basic(loginUser.getEmail(), loginUser.getPassword())
                    .contentType(ContentType.JSON);
    }
}
