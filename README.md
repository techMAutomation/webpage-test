Prerequisites:
-------------

- Java8 - Download link - https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
- Maven - Download link - https://maven.apache.org/download.cgi
- IntelliJ Community Edition - https://www.jetbrains.com/idea/download/

Install and set path for Java8 and Maven. 
For Windows - add path to System variables
For MAC - add path in "~/.bash_profile" file

Steps to run the project:
-------------------------
1. Download/Clone the project from GitHub
2. Open the IntelliJ IDE and Import the project as Maven project
3. Open Command prompt or Terminal and navigate to the project folder then run the following command:
mvn clean install -Dtags="@test"
4. To view the test scenarios, navigate to the file: src/test/resources/test.feature

Note: 
-----
- Depends on the tag name "@test", all feature files will be executed.
- To run a specific scenario from a single feature file add a new tag for ex: "@wip" above the scenario and then add "@wip" in the tags section under the CucumberRunnerTest file.
Finally, run the following command : **mvn clean install -Dtags="@wip"**, so that it will only run the scenarios tagged with "@wip"
