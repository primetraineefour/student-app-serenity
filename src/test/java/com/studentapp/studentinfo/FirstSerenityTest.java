package com.studentapp.studentinfo;

import com.studentapp.constants.EndPoints;
import com.studentapp.testbase.TestBase;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Title;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by Jay
 */
//@RunWith(SerenityRunner.class)
public class FirstSerenityTest extends TestBase {

    @Test
    public void getAllStudents() {
        SerenityRest.rest()
                .given()
                .when()
                .get(EndPoints.GET_ALL_STUDENT)
                .then()
                .statusCode(200);
    }

    @Test
    public void thisIsFailing() {
        SerenityRest.rest()
                .given()
                .when()
                .get(EndPoints.GET_ALL_STUDENT)
                .then()
                .statusCode(400);
    }

    @Pending
    @Test
    public void thisIsAPending(){

    }

    @Ignore
    @Test
    public void thisIsASkipped(){

    }

    @Test
    public void thisIsATestWithError(){
        System.out.println("This is an error " +(5/0));

    }

    @Test
    public void thisIsACompromised() throws FileNotFoundException {
        File file = new File("E://file.txt");
        FileReader fr = new FileReader(file);
    }


    @Manual
    @Test
    public void thisIsAManual(){

    }

    @Title("This test will get the information of all student")
    @Test
    public void test001() {
        SerenityRest.rest()
                .given()
                .when()
                .get(EndPoints.GET_ALL_STUDENT)
                .then()
                .statusCode(200);
    }



}
