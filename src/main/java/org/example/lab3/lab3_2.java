package org.example.lab3;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class lab3_2 {

    private static final String baseUrl = "https://petstore.swagger.io/v2";
    private static final String PET = "/pet";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = baseUrl;
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.requestSpecification = new RequestSpecBuilder().setContentType(ContentType.JSON).build();
        RestAssured.responseSpecification = new ResponseSpecBuilder().build();
    }

    @Test
    public void testGetPet() {
        long petId = 8;
        given().get(PET + "/" + petId).then().statusCode(200);
    }

    @Test
    public void testUpdatePet() {
        long petId = 8;
        Map<String, ?> body = Map.of(
                "id", petId,
                "name", "Mikhailo",
                "status", "available"
        );
        given().body(body).put(PET).then().statusCode(200);
    }

    @Test
    public void testAddNewPet() {
        Map<String, ?> body = Map.of(
                "id", 122232,
                "name", "Krasniuk",
                "status", "available"
        );

        given().body(body).post(PET).then().statusCode(200);
    }
}
