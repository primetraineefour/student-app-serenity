package com.studentapp.studentinfo;

import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.assertThat;

/**
 * Created by Jay
 */
@RunWith(SerenityRunner.class)
public class StudentCURDTestWithSteps extends TestBase {

    static String firstName = "PRIMEUSER" + TestUtils.getRandomValue();
    static String lastName = "PRIMEUSER" + TestUtils.getRandomValue();
    static String programme = "Api Testing";
    static String email = TestUtils.getRandomValue() + "xyz@gmail.com";
    static int studentId;

    @Steps
    StudentSteps studentSteps;


    @Title("This will create a new student")
    @Test
    public void test001(){

        List<String> courses = new ArrayList<>();
        courses.add("JAVA");
        courses.add("API");

        studentSteps.createStudent(firstName,lastName, email, programme, courses).statusCode(201);

    }

    @Title("Verify if the student was added to the application")
    @Test
    public void test002() {
        HashMap<String, Object> value = studentSteps.getStudentInfoByFirstName(firstName);
        assertThat(value, hasValue(firstName));
        studentId = (int) value.get("id");
    }

    @Title("Update the user information and verify the updated information")
    @Test
    public void test003(){

        firstName = firstName+ "_Updated";

        List<String> courses = new ArrayList<>();
        courses.add("JAVA");
        courses.add("API");

        studentSteps.updateStudent(studentId, firstName, lastName, email, programme,courses).statusCode(200);

        HashMap<String, Object> value = studentSteps.getStudentInfoByFirstName(firstName);
        assertThat(value, hasValue(firstName));

    }

    @Title("Delete the student and verify if the student is deleted!")
    @Test
    public void test004() {
        studentSteps.deleteStudent(studentId).statusCode(204);
        studentSteps.getStudentByID(studentId).statusCode(404);
    }
}
