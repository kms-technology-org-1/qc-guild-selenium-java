# qc-guild-selenium-java
qc-guild-selenium-java

### Technical stacks:
* Java
* Selenium Webdriver
* Cucumber: Gherkins, Gluecode, Report
* TestNG
* Patterns: POM + PageFactory

### IntelliJ IDEA - Gherkin, Cucumber support:
* Install '_**Cucumber for Java**_' Plugin
* Install '_**Cucumber +**_' Plugin
* Install '_**Gherkin**_' Plugin

### Gherkin best practices:
* [BDD 101: WRITING GOOD GHERKIN](https://automationpanda.com/2017/01/30/bdd-101-writing-good-gherkin/)

### How to run tests:
```java
mvn clean test -Denv=qa -Dbrowser=chrome -Dheadless=false -Dcucumber.filter.tags="@register or @login"
```
* **-Denv=**: values will be 'dev' or 'qa' or 'ct'. **Default value is 'qa'.**
* **-Dbrowser=**: values will be 'chrome' or 'firefox' or 'edge'. **Default value is 'chrome'.**
* **-Dheadless=**: values will be 'true' or 'false'. **Default value is 'false'.**
* **-Dcucumber.filter.tags=**: put tags representing the scenario/feature you want to run. You can run with single tag and multiple/combine tags:
  * Single tag: 
  ```java 
    -Dcucumber.filter.tags="@register" 
    ```
  * Multiple/combine tags:
    * Run scenarios/features MUST contain both @register and @login tags: 
    ```java 
    -Dcucumber.filter.tags="@register and @login" 
    ```
    * Run scenarios/features containing either @register or @login tags:
    ```java 
    -Dcucumber.filter.tags="@register or @login"
    ```
    * Run scenarios/features containing @register tag and IGNORE scenarios/features containing @login tag:
    ```java 
    -Dcucumber.filter.tags="@register and ~@login"
    ```