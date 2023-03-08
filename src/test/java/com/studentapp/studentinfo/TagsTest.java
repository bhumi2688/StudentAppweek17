package com.studentapp.studentinfo;

import com.studentapp.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;

import static net.serenitybdd.rest.RestRequests.when;
import static net.serenitybdd.rest.SerenityRest.given;

@RunWith(SerenityRunner.class)
public class TagsTest extends TestBase {
@WithTag("student.feature:NEGATIVE ")
    @Title("provide 405 status code with incorrect method")
    @Test
    public void invalidMethod() {
        given()
                .when()
                .get("list")
                .then()
                .statusCode(403)
                .log()
                .all();

    }


@WithTag("student.feature")
    @Title("This test will verify if a status code of 200 is returned for GET request")
    @Test
    public void verifyIfTheStatusCodeIs200() {

    }

    @Title("This test will provide an error code of 400 when user tries to access an invalid resource")
    @Test
    public void inCorrectResource() {

    }

}
