# Assignments
### INPUTS:
- **Initial framework**: Selenium + Java + Cucumber + TestNG + POM + Page Factory
- **Java coding conventions**: [Code Conventions for the Java Programming Language](https://www.oracle.com/java/technologies/javase/codeconventions-contents.html)
- **Gherkins best practices**: [BDD 101: Writing Good Gherkin | Automation Panda](https://automationpanda.com/2017/01/30/bdd-101-writing-good-gherkin/)
- **Web testing**: [OrangeHRM](https://opensource-demo.orangehrmlive.com/)
  - **Admin testing account**:
      - **Username**: Admin
      - **Password**: admin123

### REQUIREMENTS:
**_Based on the initial framework and web testing provided above, let’s write automation tests for 3 User Stories below:_**

```
User Story 01:  Password Change
As a user, I want to be able to change my password in my ESS account.

Acceptance Criteria:
- The ESS user should be able to access the password change functionality.
- After changing the password, the ESS user should be able to log in successfully using the new password.
```

```
User Story 02: Update My Info
As a user, I want to be able to update my information in my ESS account.

Acceptance Criteria:
- The user should have access to edit their personal information.
- Changes made by the user should be reflected accurately in the account information.
- Any updates should adhere to the records submitted by the admin account.
```

```
User Story 03: Leave Record Submission and Approval
As an ESS user, I want to be able to submit my leave records.
As an Admin user, I want to be able to approve or reject leave records submitted by ESS users.

Acceptance Criteria:
- Users should have access to a leave submission feature.
- Users should be able to specify the type of leave (e.g., vacation, personal,...) and the duration.
- Submitted leave records should be visible to admins for review.
- Admins should have the ability to approve or reject leave records.
- Approved leave records should be reflected accurately in the system's records.
- Rejected leave records should prompt users to resubmit with necessary adjustments.
```

### OUTPUTS:
1. Create your feature branch from the latest main of this repository.
   - Your feature branch name has to follow this pattern:
     
   `AssessmentProgram2024/<yourName>/Selenium_Java_Assignments`

   Ex: AssessmentProgram2024/NamHoang/Selenium_Java_Assignments


2. After completing all requirements above, open a new Pull Request following this pattern:
   
    `AssessmentProgram2024 - <yourName>: Selenium Java Assignments`

   Ex: AssessmentProgram2024 - Nam Hoang:  Selenium Java Assignments


3. Submit your pull request link by sending an email to us with subject pattern as below:
  
   ` AssessmentProgram2024 - <yourName>: Selenium Java Assignments`

      Ex: AssessmentProgram2024 - Nam Hoang: Selenium Java Assignments