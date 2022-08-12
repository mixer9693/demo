package ru.demo.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import ru.demo.dto.CommentInputDto;


import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(ru.demo.TestConfig.class)
class CommentControllerIT  {

    @LocalServerPort
    private int port;

    @Autowired ObjectMapper objectMapper;

    @BeforeAll
    static void setup(@Autowired DataSource dataSource,
                      @Autowired PlatformTransactionManager transactionManager) {
        new TransactionTemplate(transactionManager).execute((ts) -> {
            try (Connection conn = dataSource.getConnection()) {
                ScriptUtils.executeSqlScript(conn, new ClassPathResource("sql/fixtures.sql"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        });
    }

    @Test
    void create() throws JsonProcessingException {
        CommentInputDto commentDto = new CommentInputDto();
        commentDto.setText("testComment");

        Response response = given()
                .port(port).contentType("application/json")
                .body(objectMapper.writeValueAsString(commentDto))
                .when()
                .post("/issues/{id}/comments", 3);

        response.then().assertThat()
                .statusCode(200)
                .body("$", hasKey("id"));
        System.out.println(response.getBody().asString());

       given()
               .port(port).contentType("application/json")
               .body("{}")
               .when()
               .post("/issues/{id}/comments", 3)
               .then().assertThat()
               .statusCode(400);

        given()
                .port(port).contentType("application/json")
                .body(objectMapper.writeValueAsString(new CommentInputDto()))
                .when()
                .post("/issues/{id}/comments", 3)
                .then().assertThat()
                .statusCode(400);

        given()
                .port(port).contentType("application/json")
                .body(objectMapper.writeValueAsString(commentDto))
                .when()
                .post("/issues/{id}/comments", 33333)
                .then().assertThat()
                .statusCode(404);

    }

    @Test
    void getCommentsByIssueId(){
        given()
                .port(port).contentType("application/json")
                .get("/issues/{id}/comments", 1)
                .then()
                .assertThat()
                .statusCode(200)
                .body("$", hasSize(2));
// TODO fail
//        given()
//                .port(port).contentType("application/json")
//                .get("/issues/{id}/comments", 11111)
//                .then()
//                .assertThat()
//                .statusCode(404);
    }

    @Test
    void update() throws JsonProcessingException {
        CommentInputDto editedComment = new CommentInputDto();
        editedComment.setText("NewText");

        Response response = given()
                .port(port).contentType("application/json")
                .body(objectMapper.writeValueAsString(editedComment))
                .patch("/comments/{id}", 10);

        System.out.println(response.getBody().asString());
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("text", equalTo(editedComment.getText()));

        given()
                .port(port).contentType("application/json")
                .body(new CommentInputDto())
                .patch("/comments/{id}", 10)
                .then()
                .assertThat()
                .statusCode(400);

//TODO fail
//        given()
//                .port(port).contentType("application/json")
//                .body(editedComment)
//                .patch("/comments/{id}", 101010)
//                .then()
//                .assertThat()
//                .statusCode(404);
    }

}
