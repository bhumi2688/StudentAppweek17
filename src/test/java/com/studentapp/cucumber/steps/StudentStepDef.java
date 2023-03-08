package com.studentapp.cucumber.steps;

import com.studentapp.studentinfo.StudentSteps;
import com.studentapp.utils.TestUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;

public class StudentStepDef {
    static ValidatableResponse response;
    static String firstName = null;
    static String lastName = null;
    static String email = null;
    static String programme = null;

    static int StudentId;

    @Steps
    StudentSteps studentSteps;
    @When("^User sends a GET request to list endpoint$")
    public void user_sends_a_GET_request_to_list_endpoint() {
      response =studentSteps.getAllStudentInfo();
    }
    @Then("^User must get back a valid status code (\\d+)$")
    public void user_must_get_back_a_valid_status_code(int StatusCode) {
        response.statusCode(StatusCode);
    }
    @When("^I create a new student by providing the information \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void iCreateANewStudentByProvidingTheInformation(String firstName, String _lastName, String email, String programme, String courses)  {
        List<String> coursesList = new ArrayList<>();
        coursesList.add(courses);
        lastName = TestUtils.getRandomValue() + _lastName;
        response = studentSteps.createStudent(firstName,lastName,email,programme,coursesList);
    }

    @Then("^I verify that the student with lastName is created$")
    public void iVerifyThatTheStudentWithLastNameIsCreated() {
        response.statusCode(201);
        HashMap<String, Object> studentMap = studentSteps.getStudentInfoBylastName(lastName);
        Assert.assertThat(studentMap, hasValue(lastName));

    }
    @When("^New student is updated with new \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void newStudentIsUpdatedWithNew(String firstName, String _lastName, String email, String programme, String courses)  {
        List<String> coursesList = new ArrayList<>();
        coursesList.add(courses);
        firstName = TestUtils.getRandomValue() + firstName;
        lastName = TestUtils.getRandomValue() + _lastName;
        email = TestUtils.getRandomValue() + email;
        programme = TestUtils.getRandomValue() + programme;
        response = studentSteps.updateStudent(StudentId,firstName,lastName,email,programme,coursesList);
    }


    @When("^I have deleted student by id$")
    public void iHaveDeletedStudentById() {
        response = studentSteps.deleteStudentInfoById(StudentId);
    }

    @Then("^I verify that student is deleted$")
    public void iVerifyThatStudentIsDeleted() {
        response = studentSteps.getStudentInfoById(StudentId);
    }


}
