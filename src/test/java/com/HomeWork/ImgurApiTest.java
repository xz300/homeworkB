package com.HomeWork;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class ImgurApiTest {


    @Test
    @Order(1)
    @DisplayName("Получение инфомрации об аккаунте")
    void testGetAccBase() {
        given()
                .auth()
                .oauth2("4828e0dda619306a4de9d6e1c2a66e7d1090bbf1")
                .expect()
                .body("data.id", is(154665853))
                .body("data.created", is(1631645039))
                .body("data.url", is("xz300"))
                .body("success", is(true))
                .body("data.is_blocked", is(false))
                .statusCode(200)
                .when()
                .get("https://api.imgur.com/3/account/xz300");
//                .body()
//                .prettyPrint();
    }

    @Test
    @Order(2)
    @DisplayName("Создание комментария")
    void testAddComment() {
        given()
                .auth()
                .oauth2("4828e0dda619306a4de9d6e1c2a66e7d1090bbf1")
                .formParam("image_id", "25wwXL9")
                .formParam("comment", "Home Work 7")
                .expect()
//                .body("data.id", is(2138573521))
                .body("success", is(true))
                .statusCode(200)
                .when()
                .post("https://api.imgur.com/3/comment");
//                .body()
//                .prettyPrint();

    }

    @Test
    @Order(3)
    @DisplayName("Получение информации по комментарию")
    void testGetComment() {
        given()
                .auth()
                .oauth2("4828e0dda619306a4de9d6e1c2a66e7d1090bbf1")
                .expect()
                .body("data.id", is(2138606517))
                .body("data.image_id", is("25wwXL9"))
                .body("data.author_id", is(154665853))
                .body("data.comment", is("xz300"))
                .body("success", is(true))
                .body("data.on_album", is(true))
                .body("data.datetime", Matchers.notNullValue())
                .statusCode(200)
                .when()
                .get("https://api.imgur.com/3/comment/2138606517");
    }

    @Test
    @Order(4)
    @DisplayName("UP Comment")
    void testPostVote() {
        given()
                .auth()
                .oauth2("4828e0dda619306a4de9d6e1c2a66e7d1090bbf1")
                .expect()
                .body("data", is(true))
                .body("success", is(true))
                .statusCode(200)
                .when()
                .post("https://api.imgur.com/3/comment/2137293189/vote/up");
    }

    @Test
    @Order(5)
    @DisplayName("Получение информации по альбому")
    void testGetAlbom() {
        given()
                .auth()
                .oauth2("4828e0dda619306a4de9d6e1c2a66e7d1090bbf1")
                .expect()
                .body("data.id", is("VXG3v3o"))
                .body("data.title", is("My dank meme album"))
                .body("data.deletehash", Matchers.notNullValue())
                .body("success", is(true))
                .statusCode(200)
                .when()
                .get("https://api.imgur.com/3/album/VXG3v3o");
    }

    @Test
    @Order(6)
    @DisplayName("Создание альбома")
    void testAddAlbom() {
        given()
                .auth()
                .oauth2("4828e0dda619306a4de9d6e1c2a66e7d1090bbf1")
                .expect()
                .body("data.id", Matchers.notNullValue())
                .body("data.deletehash", Matchers.notNullValue())
                .expect()
                .body("success", is(true))
                .statusCode(200)
                .when()
                .post("https://api.imgur.com/3/album");
    }

    @Test
    @Order(7)
    @DisplayName("Получение информации по картинке")
    void testGetImage() {
        given()
                .auth()
                .oauth2("4828e0dda619306a4de9d6e1c2a66e7d1090bbf1")
                .expect()
                .body("data.id", is("wbnujeG"))
                .body("data.description", is("real comment"))
                .body("success", is(true))
                .statusCode(200)
                .when()
                .get("https://api.imgur.com/3/image/wbnujeG");
    }
    @Test
    @Order(7)
    @DisplayName("Загрузка картинки")
    void testPostImage() {
        given()
                .auth()
                .oauth2("4828e0dda619306a4de9d6e1c2a66e7d1090bbf1")
                .multiPart("image", new File( "/D:/Documents/Pictures/WTmKMFehGXI.jpg"))
                .expect()
                .body("data.id", Matchers.notNullValue())
                .body("data.deletehash", Matchers.notNullValue())
                .expect()
                .body("success", is(true))
                .statusCode(200)
                .when()
                .post("https://api.imgur.com/3/upload");
    }
}

