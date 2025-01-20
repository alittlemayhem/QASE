package tests.ui;

import io.qameta.allure.Description;
import models.defects.CreateDefectRq;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.util.List;

import static adapters.DefectsAPI.createDefect;
import static adapters.ProjectAPI.createProject;
import static adapters.ProjectAPI.deleteProjectByCode;
import static org.testng.Assert.assertEquals;

public class DefectTest extends BaseTest {

    CreateDefectRq rq = CreateDefectRq.builder()
            .title("Defect 1")
            .actualResult("Result 1")
            .severity(2)
            .build();

    @Test(testName = "Defect creation", description = "Test defect creation")
    @Description("Check defect creation via UI with precondition: project creation using API.")
    public void checkCreateDefect() {
        loginPage.open()
                .login(user, password);
        if (!projectsPage.checkIfProjectExists()) {
            createProject(proj_rq);
        }
        String actualMessage = defectsPage.open("QASE")
                .isPageOpened()
                .createNewDefect()
                .isPageOpened()
                .fillRequiredDefectFields("Defect 1", "Result 1", "Major")
                .createDefect()
                .successMessage();

        softAssert.assertEquals(actualMessage,
                "Defect was created successfully!",
                "Wrong message is shown or defect was not created.");

        String defectCode = defectsPage.getDefectCode("Defect 1");
        softAssert.assertEquals(defectCode,
                "D-1",
                "Incorrect defect code.");

        List<WebElement> defects = defectsPage.getDefectsOnPage();
        List<WebElement> defectCodes = defectsPage.getCodeDefectsOnPage();

        softAssert.assertTrue(defectsPage.isDefectOnPage(defects, "Defect 1"),
                "Defect was not created or title is incorrect.");

        softAssert.assertTrue(defectsPage.isDefectCodeCorrect(defects, "Defect 1", defectCodes, "D-1"),
                "Defect and coded do not match.");

        deleteProjectByCode("QASE");
    }

    @Test(testName = "Edit defect", description = "Test defect modification")
    @Description("Check defect modification via UI with preconditions: project and defect creation using API.")
    public void checkEditDefect() {
        loginPage.open()
                .login(user, password);
        if (!projectsPage.checkIfProjectExists()) {
            createProject(proj_rq);
        }
        createDefect(rq, "QASE");
        String actualMessage = defectsPage
                .open("QASE")
                .isPageOpened()
                .editDefect()
                .fillRequiredDefectFields("UPDATED defect", "Updated result", "Normal")
                .updateDefect()
                .successMessage();

        assertEquals(actualMessage,
                "Defect was edited successfully!",
                "Wrong message is shown or defect was not edited.");

        deleteProjectByCode("QASE");
    }

    @Test(testName = "Delete defect", description = "Test defect removal")
    @Description("Check defect deletion via UI with preconditions: project and defect creation using API.")
    public void checkDeleteDefect() {
        loginPage.open()
                .login(user, password);
        if (!projectsPage.checkIfProjectExists()) {
            createProject(proj_rq);
        }
        createDefect(rq, "QASE");
        String actualMessage = defectsPage
                .open("QASE")
                .isPageOpened()
                .modifyOrDeleteDefect("Delete")
                .acceptActionMessage("Delete")
                .successMessage();

        assertEquals(actualMessage,
                "Defect [D-1] was successfully deleted.",
                "Wrong message is shown or defect was not removed.");

        deleteProjectByCode("QASE");
    }
}
