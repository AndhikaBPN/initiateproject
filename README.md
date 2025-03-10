# initiateproject
Custom Automation Structure Project using Java, Selenium, Cucumber, TestNG

Step:
1. Clone this branch
2. run command ```mvn clean install -U -dSkipTests``` to install or update all used dependencies
3. create feature file inside ```src/test/resources.features``` folder
4. put the feature file path inside ```src/test/com/projectname/testrunner/TestRunner.java``` file inside ```@CucumberOptions.features``` array
5. to run the test run command ```mvn test```
