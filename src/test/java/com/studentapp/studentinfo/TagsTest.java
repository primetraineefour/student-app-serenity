package com.studentapp.studentinfo;

import com.studentapp.constants.EndPoints;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;

/**
 * Created by Jay
 */
//@RunWith(SerenityRunner.class)
public class TagsTest {

    @WithTag("studentrfeature:NEGATIVE")
    @Title("Provide a 405 status code when incorrect HTTP method is used to access a resource")
    @Test
    public void inValidMethod() {
        SerenityRest.rest()
                .given()
                .when()
                .post(EndPoints.GET_ALL_STUDENT)
                .then()
                .statusCode(405)
                .log().all();
    }

    @WithTags({
            @WithTag("studentrfeature:POSITIVE"),
            @WithTag("studentrfeature:SMOKE")
    })
    @Test
    @Title("This test will verify if a status code of 200 is returned for GET request")
    public void verifyIfTheStatusCodeIs200() {
        SerenityRest.rest()
                .given()
                .when()
                .get(EndPoints.GET_ALL_STUDENT)
                .then()
                .statusCode(200)
                .log().all();

    }

    @WithTags({
            @WithTag("studentrfeature:NEGATIVE"),
            @WithTag("studentrfeature:SMOKE")
    })
    @Title("This test will provide an error code of 400 when user tries to access an invalid resource")
    @Test
    public void incorrectResource() {
        SerenityRest.rest()
                .given()
                .when()
                .get("/list123")
                .then()
                .statusCode(400)
                .log().all();

    }

}
