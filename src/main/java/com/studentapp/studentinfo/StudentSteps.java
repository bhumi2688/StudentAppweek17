package com.studentapp.studentinfo;

import com.studentapp.constants.EndPoints;
import com.studentapp.model.StudentPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.List;


public class StudentSteps {
    @Step("getting all information :{0}")
    public ValidatableResponse getAllStudentInfo(){
        return SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_STUDENT)
                .then();
    }
    @Step("creating student with firstName :{0},lastName :{1},email :{2},programme :{3},courses :{4}")
    public ValidatableResponse createStudent(String firstName, String lastName, String email, String programme, List<String> courseList){
        StudentPojo pojo = new StudentPojo();
        pojo.setFirstName(firstName);
        pojo.setLastName(lastName);
        pojo.setEmail(email);
        pojo.setProgramme(programme);
        pojo.setCourses(courseList);

        return SerenityRest.given()
                .log().all()
                .header("Content-Type","application/json")
                .body(pojo)
                .when()
                .post()
                .then().statusCode(201);

    }
    @Step("getting student information by lastName:{0}")
    public HashMap<String, Object> getStudentInfoBylastName(String lastName){
        String part1 = "findAll{it.lastName=='";
        String part2 = "'}.get(0)";

        return SerenityRest.given()
                .log().all()
                .when()
                .get(EndPoints.GET_ALL_STUDENT)
                .then()
                .statusCode(200)
                .extract().path(part1 + lastName + part2);
    }
    @Step("Update student information with studentID:{0},firstName :{1},lastName :{2},email :{3},programme :{4},List<String> courseList :{5}")
    public ValidatableResponse updateStudent(int studentId,String firstName,String lastName,String email,String programme,List<String> courseList){
        StudentPojo pojo = new StudentPojo();
        pojo.setFirstName(firstName);
        pojo.setLastName(lastName);
        pojo.setEmail(email);
        pojo.setProgramme(programme);
        pojo.setCourses(courseList);

        return SerenityRest.given()
                .log().all()
                .header("Content-Type","application/json")
                .pathParam("studentID",studentId)
                .body(pojo)
                .when()
                .put(EndPoints.UPDATE_STUDENT_BY_ID)
                .then();

    }
    @Step("Deleteing student information with studentId :{0}")
    public ValidatableResponse deleteStudentInfoById(int studentId){
        return SerenityRest.given()
                .pathParam("studentID",studentId)
                .when()
                .delete(EndPoints.DELETE_STUDENT_BY_ID)
                .then();

    }
    @Step("Getting student infor by studentId :{0}")
    public ValidatableResponse getStudentInfoById(int studentId){
        return SerenityRest.given()
                .pathParam("studentID",studentId)
                .when()
                .get(EndPoints.GET_SINGLE_STUDENT_BY_ID)
                .then();
    }

}
