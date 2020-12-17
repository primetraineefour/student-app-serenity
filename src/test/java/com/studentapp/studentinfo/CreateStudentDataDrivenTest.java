package com.studentapp.studentinfo;

import com.studentapp.testbase.TestBase;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jay
 */
//@Concurrent(threads = "4x")
@UseTestDataFrom("src\\test\\java\\resources\\testdata\\studentinfo.csv")
//@RunWith(SerenityParameterizedRunner.class)
public class CreateStudentDataDrivenTest extends TestBase {

    private String firstName;
    private String lastName;
    private String email;
    private String programme;
    private String course1;
    private String course2;


    @Steps
    StudentSteps studentSteps;

    @Title("DataDriven Test for adding multiple students to the student app")
    @Test
    public void createMultipleStudents(){
        List<String> courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);
        studentSteps.createStudent(firstName, lastName, email, programme, courses);
    }

}


