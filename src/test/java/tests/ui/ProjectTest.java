package tests.ui;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import tests.BaseTest;

import static adapters.ProjectAPI.deleteProjectByCode;

public class ProjectTest extends BaseTest {

    @Test(testName = "Project with required fields", description = "Create project with filled name and code.")
    @Description("Creation of new project named 'QASE' and with code 'AQ'")
    public void checkCreateProjectWithRequiredFields() {
        loginPage.open()
                .login(user, password)
                .isPageOpened()
                .createProject()
                .isModalOpened()
                .setProjectName("QASE")
                .setProjectCode("AQ")
                .clickCreateProject();

        String actualTitle = createdProjectPage.getProjectTitle();

        softAssert.assertEquals(actualTitle,
                "AQ repository",
                "Incorrect name of the project.");

        deleteProjectByCode("AQ");
    }

    @Test(testName = "Project with name only", description = "Create project with filled name.")
    @Description("Creation of new project named 'QASE'")
    public void checkCreateProjectNameOnly() {
        loginPage.open()
                .login(user, password)
                .isPageOpened()
                .createProject()
                .setProjectName("QASE")
                .clickCreateProject();

        String actualTitle = createdProjectPage.getProjectTitle();

        softAssert.assertEquals(actualTitle,
                "QASE repository",
                "Incorrect name of the project.");

        deleteProjectByCode("QASE");
    }

    @Test(testName = "Public project creation", description = "Create project with public access.")
    @Description("Creation of new project named 'QASE' with access mode 'Public'")
    public void checkCreatePublicProject() {
        loginPage.open()
                .login(user, password)
                .isPageOpened()
                .createProject()
                .setProjectName("QASE")
                .setRadioButtonValue("public")
                .clickCreateProject();

        String actualTitle = createdProjectPage.getProjectTitle();

        softAssert.assertEquals(actualTitle,
                "QASE repository",
                "Incorrect name of the project.");

        deleteProjectByCode("QASE");
    }

    @Test(testName = "Group project creation", description = "Create project with private access (group mode).")
    @Description("Creation of new project named 'QASE' with access mode 'Private' for specific group.")
    public void checkCreatePrivateProjectGroupAccess() {
        loginPage.open()
                .login(user, password)
                .isPageOpened()
                .createProject()
                .setProjectName("QASE")
                .setRadioButtonValue("group")
                .selectGroupOwner()
                .clickCreateProject();

        String actualTitle = createdProjectPage.getProjectTitle();

                softAssert.assertEquals(actualTitle,
                "QASE repository",
                "Incorrect name of the project.");

        deleteProjectByCode("QASE");
    }
}
