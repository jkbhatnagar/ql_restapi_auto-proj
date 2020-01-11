# REST API Automation - Java - RestAssured - Cucumber - Extent Reports

[![Build Status](https://travis-ci.com/jkbhatnagar/ql_restapi_auto-proj.svg?branch=develop)](https://travis-ci.com/jkbhatnagar/ql_restapi_auto-proj)

## USAGE

### Travis CI Builds

    https://travis-ci.com/jkbhatnagar/ql_restapi_auto-proj/builds
    

### Run Locally:
 - Pre-requisites: java version 13, jdk version 13, maven (optional), internet access, 
 - Download or clone the 'develop' branch locally.
 - Run all TCs : Running without local maven : 
 
     ```./mvnw clean test -Dcucumber.options="--tags @RegressionTest --plugin com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:output --glue stepdefs"```
 - Run all TCs : Running from local maven : 
 
    ```mvn clean test -Dcucumber.options="--tags @RegressionTest --plugin com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:output --glue stepdefs"```
 - Run Smoke TCs : Running without local maven : 
 
    ```./mvnw clean test -Dcucumber.options="--tags @SmokeTest --plugin com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:output --glue stepdefs"```

### Reports:
 - Extent report is generated at testreport/HtmlReport/ExtentHtml.html
 - Report provides detailed scenarios and step execution summary, with passed - failed - skipped results
 - Sample report screens in screenshots directory

### Maintenance:
- Don't change RunCucumberTest, extent.properties, extent-config.xml
- Add global constants to src/test/java/utils/UtilConstants.
- Add new Cucumber feature files to src/test/features.
- Add new Step Definition files to src/test/stepdefs.

## TASK LIST

### Completed
- [x] Add Cucumber support
- [x] Add Cucumber Table support
- [x] Add RestAssured support
- [x] Add Extent Report support
- [x] Use Cucumber @Tags to categorize Test Scenarios into @SmokeTest and @RegressionTest
- [x] Read constants and globals from Contants.java or a .properties file

### Pending
- [ ] Split Step definition and feature for different services to separate files
- [ ] Create model POJO classes for Success and Failure resposes and extract response in the for advanced assertions, path assertions, and sharing objects between inflight test cases
- [ ] Add assertion failure comments (if required)
- [ ] Pretty print request and responses in logs
- [ ] Generate a log file
- [ ] Use YAML or Properties file based test data
- [ ] Add test data in Cucumber steps as "Example" and then use <placeholder> tags to avoid repeating same data in Given and Then (must if reading data from external file)
- [ ] Add Environment changer so that same code and assertions can be used with different data for different Test Environments (SIT/UAT/PROD)
- [ ] Use cucumber-picocontainer to add Dependency Injection to share inflight test objects between test cases

        https://www.coveros.com/using-dependency-injectors-simplify-code-cucumber/
        http://www.thinkcode.se/blog/2017/04/01/sharing-state-between-steps-in-cucumberjvm-using-picocontainer

- Move common tasks and assertions to utlity class

        https://github.com/jaganduraisamy/RestAssured-BDD-Java/blob/master/src/test/java/utilities/Utils.java

## REPORT SCREENS

![Dashboard-All](/screenshots/Dashboard-All.png)

![Dashboard-Failed](/screenshots/Dashboard-Failed.png)

![TestGroups](/screenshots/TestGroups.png)

![Defects](/screenshots/Defects.png)


## RESOURCE REFERENCES
- Cucumber base

        https://github.com/cucumber/cucumber-java-skeleton

- Samples

        https://github.com/angiejones/restassured-with-cucumber-demo
        https://github.com/raghwendra-sonu/REST-assured_Cucumber

