# Final work - Automated Testing of QASE

## Tech stack

The following tools were used:

- **Programming language:** Java 11
- **Testing framework:** TestNG
- **UI testing framework:** Selenium 
- **API testing:** RestAssured
- **Build automation tool:** Maven 
- **Reports:** Allure Report 
- **Version control:** Git 
- **CI/CD:** GitHub Actions

## Functionality

The following test scenarios were implemented:

1. **UI testing**
  - Check work of project creation, test case, test suit, defects, test plan creation, modification and removal.

2. **API testing**
  - Test work of REST API: CRUD-operations.

3. **Work to do**
- Some functionality for UI and API testing is yet to be implemented (to be in accordance with checklist provided below).

## Project structure

```plaintext
QASE/
├── src/
│   └── test/       
│       ├── adapters/     
│       ├── dto/    
│       ├── models/     
│       ├── pages/  
│       ├── tests/     
│           ├── api/    
│           ├── ui/     
│       ├── utils/     
│       ├── wrappers/    
│   └── resources/
├── pom.xml   
└── README.md       
```

## Install and run

### Preconditions
- Installed **Java 11**.
- **Maven**.
- **Git**.

### Installation
1. Clone project repository:
   ```bash
   git clone https://github.com/alittlemayhem/QASE.git
   ```
2. Switch to project repository:
   ```bash
   cd QASE
   ```
3. Install dependecies:
    ```bash
    mvn install
    ```
4. Specify your credentials in config.properties file.

### Run tests
- To run all tests:
  ```bash
  mvn test 
  ```

- Generate allure report:
  ```bash
  mvn allure:serve
  ```

## Reporting test results

Reports of test results are automatically saved to `target/allure-results`.
To check allure reports run:
```bash
allure serve target/allure-results
```

## Code examples

### UI test
```java
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
```

### API test
```java
@Test(testName = "Create Defect", description = "Check defect creation")
@Description("Test full defect cycle using all API functions.")
public void checkCreateDefect() {
  createProject(proj_rq);
  Response rs = createDefect(rq, "QASE");
  softAssert.assertEquals(Optional.ofNullable(rs.getStatus()), true);

  DefectResponse createdDefect = getSpecificDefect("QASE", "1");
  softAssert.assertEquals(createdDefect.getResult().getStatus(),
          "open",
          "Wrong initial defect status.");

  deleteSpecificDefect("QASE", "1");
  deleteProjectByCode("QASE");
}
```

## Support

If you have questions or suggestion of improvement, create issue in repository or connect with me via:
- Email: jadwiganiewdach@gmail.com
- LinkedIn: [Jadwiga Niewdach](https://www.linkedin.com/in/jadwiga-niewdach-7a433a220?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app)

## Checklist
### 1. Projects page
- [ ] 1.1 Create new project 
- [ ] 1.2 Edit existing project
- [ ] 1.3 Filter projects by status
- [ ] 1.4 Check sorting projects by columns

### 2. Work with created project
- #### 2.1 Create test suite
  - [ ] 2.1.1 'Create new suite' button
  - [ ] 2.1.2 '+ Suite' button
  - [ ] 2.1.3 Create suite from three-dot menu
  - [ ] 2.1.4 Create several suites with first parent one
  - [ ] 2.1.5 Edit top suite
  - [ ] 2.1.6 Edit nested (child) suite
  - [ ] 2.1.7 Clone suite from top toolbar menu
  - [ ] 2.1.8 Clone suite via three-dot menu
  - [ ] 2.1.9 Delete suite via top toolbar
  - [ ] 2.1.10 Delete suite via three-dot menu
- #### 2.2 Create test case
  - [ ] 2.2.1 'Create new case' button
  - [ ] 2.2.2 '+ Case' button
  - [ ] 2.2.3 Edit case from case panel
  - [ ] 2.2.4 Edit several cases after it was checked in the table (top toolbar)
  - [ ] 2.2.5 Clone case from case panel
  - [ ] 2.2.6 Clone checked cases in the table (top toolbar)
  - [ ] 2.2.7 Delete case from case panel
  - [ ] 2.2.8 Delete selected cases in the table (top toolbar)
  - [ ] 2.2.9 Create quick case
  - [ ] 2.2.10 Copy case link to clipboard
  - [ ] 2.2.11 Start test run on selected cases
  - [ ] 2.2.12 Check case select/unselect
- #### 2.3 Check different view options 
- #### 2.4 Export test data
  - [ ] 2.4.1 Export to json format
  - [ ] 2.4.2 Export to csv format
- #### 2.5 Import test data
  - [ ] 2.5.1 Import from json
  - [ ] 2.5.2 Import from csv
  - [ ] 2.5.3 Import with replace test cases on match
- #### 2.6 Create test plan
  - [ ] 2.6.1 Create new test plan
  - [ ] 2.6.2 View created test plan (separate page)
  - [ ] 2.6.3 Edit created plan
  - [ ] 2.6.4 Delete plan from three dot menu
  - [ ] 2.6.5 Delete several plans from top toolbar
- #### 2.7 Create defects
  - [ ] 2.7.1 Create new defect
  - [ ] 2.7.2 Change status to resolved
  - [ ] 2.7.3 Change status to 'In progress'
  - [ ] 2.7.4 Change status to 'invalid'
  - [ ] 2.7.5 View defect (?) - separate page
  - [ ] 2.7.6 Edit defect
  - [ ] 2.7.7 Delete defect
  - [ ] 2.7.8 Export defects
- #### 2.8 Full test of test run
  - [ ] 2.8.1 Start new test run (from test plan)
  - [ ] 2.8.2 Remove case from run
  - [ ] 2.8.3 Wizard test
      - [ ] 2.8.3.1 View case (opens in separate window)
      - [ ] 2.8.3.2 Edit case
      - [ ] 2.8.3.3 Mute case
      - [ ] 2.8.3.4 Set status to passed
      - [ ] 2.8.3.5 Set status to failed (create defect)
      - [ ] 2.8.3.6 Set status to blocked (created defect)
      - [ ] 2.8.3.7 Set status to skipped
      - [ ] 2.8.3.8 Set status to invalid
  - [ ] 2.8.4 'Run Again' if test run is finished
  - [ ] 2.8.5 'Complete' run without running cases	

