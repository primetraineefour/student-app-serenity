package com.studentapp.studentinfo;

import com.studentapp.constants.EndPoints;
import com.studentapp.model.StudentPojo;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtils;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.assertThat;

/**
 * Created by Jay
 */
//@RunWith(SerenityRunner.class)
public class StudentCURDTest extends TestBase {

    static String firstName = "PRIMEUSER" + TestUtils.getRandomValue();
    static String lastName = "PRIMEUSER" + TestUtils.getRandomValue();
    static String programme = "Api Testing";
    static String email = TestUtils.getRandomValue() + "xyz@gmail.com";
    static int studentId;


    @Title("This will create a new student")
    @Test
    public void test001(){

        List<String> courses = new ArrayList<>();
        courses.add("JAVA");
        courses.add("API");

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName(firstName);
        studentPojo.setLastName(lastName);
        studentPojo.setEmail(email);
        studentPojo.setProgramme(programme);
        studentPojo.setCourses(courses);

        SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(studentPojo)
                .post()
                .then().log().all().statusCode(201);

    }

    @Title("Verify if the student was added to the application")
    @Test
    public void test002() {
        String p1 = "findAll{it.firstName=='";
        String p2 = "'}.get(0)";

        HashMap<String, Object> value = SerenityRest.rest().given()
                .when()
                .get("/list")
                .then()
                .statusCode(200)
                .extract()
                .path(p1+firstName+p2);
        assertThat(value, hasValue(firstName));
        studentId = (int) value.get("id");
    }

    @Title("Update the user information and verify the updated information")
    @Test
    public void test003(){

        String p1 = "findAll{it.firstName=='";
        String p2 = "'}.get(0)";

        firstName = firstName+ "_Updated";

        List<String> courses = new ArrayList<>();
        courses.add("JAVA");
        courses.add("API");

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName(firstName);
        studentPojo.setLastName(lastName);
        studentPojo.setEmail(email);
        studentPojo.setProgramme(programme);
        studentPojo.setCourses(courses);

        SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .pathParam("studentId", studentId)
                .log().all()
                .when()
                .body(studentPojo)
                .put(EndPoints.UPDATE_STUDENT_BY_ID)
                .then().log().all().statusCode(200);

        HashMap<String, Object> value = SerenityRest.rest().given()
                .when()
                .get("/list")
                .then()
                .statusCode(200)
                .extract()
                .path(p1+firstName+p2);
        assertThat(value, hasValue(firstName));

    }

    @Title("Delete the student and verify if the student is deleted!")
    @Test
    public void test004() {
        SerenityRest.rest()
                .given()
                .when()
                .delete("/"+studentId)
                .then()
                .statusCode(204);

        SerenityRest.rest()
                .given()
                .when()
                .get("/"+studentId)
                .then().statusCode(404);
    }





}
