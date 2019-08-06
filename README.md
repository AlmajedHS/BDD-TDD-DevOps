

## Technologies

* Jenkins version: 2.176.2

* SonarQube version: 7.9.1.27448

* Jacoco version: 0.8.2

* Java SDK version 11

* Twilio communication platform

## General info

In this guide, I will cover the following areas:

* [Creating BDD tests in java using cucumber](#creating-bdd-tests-in-java-using-cucumber)

* [Creating TDD unit tests in java using JUnit](#creating-tdd-unit-tests-in-java-using-junit)

* [Installing Jacoco plugin in order to get the code coverage for the created tests](#installing-jacoco-plugin-in-order-to-get-the-code-coverage-for-the-created-tests)

*  [Installing Jenkins](#installing-jenkins)

*  [Installing SonarQube and SonarScanner](#installing-sonarqube-and-sonarscanner)

* [Integrating Github reposirty with Jenkins for automatic building](#integrating-github-reposirty-with-jenkins-for-automatic-building)

* [Integrating Jenkins with SonarQube](#integrating-jenkins-with-sonarqube)

* [Integrating SonarQube with Jacoco in Jenkins to get code coverage results in SonarQube report](#integrating-sonarqube-with-jacoco-in-jenkins-to-get-code-coverage-results-in-sonarqube-report)

* [Configuring quality gates based on SonarQube report to determine the status of the Jenkins build](#configuring-quality-gates-based-on-sonarqube-report-to-determine-the-status-of-the-jenkins-build)

* [Sending Email and SMS notifications based on the build status of the project in Jenkins](#sending-email-and-sms-notifications-based-on-the-build-status-of-the-project-in-jenkins)

This repository contains the code for the TDD and BDD tests. Also, it contains the pom.xml file configuration for installing Jacoco, JUnit, and cucumber.

## Creating BDD tests in java using cucumber

1- Install Cucumber dependences in the pom.xml file that you have:

```

<dependencies>

<dependency> <groupId>io.cucumber</groupId>

<artifactId>cucumber-java</artifactId>

<version>2.3.1</version>

<scope>test</scope>

</dependency> <dependency> <groupId>io.cucumber</groupId>

<artifactId>cucumber-junit</artifactId>

<version>2.3.1</version>

<scope>test</scope>

</dependency>

<dependency> <groupId>junit</groupId>

<artifactId>junit</artifactId>

<version>4.12</version>

<scope>test</scope>

</dependency>

</dependencies>

```

2- Create a resources directory under java/test. Mark it as a Test Resource root

3- In this resource directory, create a feature file (e.g. add.feature). You could use the feature file in this repository as an example

4- Create a step definition class. You could use the addSteps class as an example

* Your steps in the feature file has to match the steps in the steps definition class.

* Every step has to be unique. Cucumber does not allow steps overlapping

5- Create a runner class to run your tests. You could use the RunCucumberTest class as an example

* If you want to run a specific feature, write the path to that feature in the CucumberOption tag that you will find in the runner class

## Creating TDD unit tests in java using JUnit

1- Install JUnit dependencies in the pom.xml file

```

<dependencies>

<dependency> <groupId>junit</groupId>

<artifactId>junit</artifactId>

<version>4.12</version>

<scope>test</scope>

</dependency>

</dependencies>

```

2- Create a class that will contain your test code under java/test. You could use the AddTwoNumberTest class as an example.

3- Add the @Test tag before your test function that you will write

## Installing Jacoco plugin in order to get the code coverage for the created tests

1- install the dependencies and plugin for Jacoco. Also, install the dependencies that will generate the report for SonarQube.

Add the following under properties:

```

<properties>

<sonar.core.codeCoveragePlugin>jacoco</sonar.core.codeCoveragePlugin>

<sonar.jacoco.reportPath>${project.basedir}/../target/jacoco.exec</sonar.jacoco.reportPath>

<sonar.language>java</sonar.language>

</properties>

```

Add the following build and plugin configurations:

```

<build>

<plugins> <plugin> <groupId>org.jacoco</groupId>

<artifactId>jacoco-maven-plugin</artifactId>

<version>0.8.2</version>

<executions> <execution> <id>prepare-agent</id>

<goals> <goal>prepare-agent</goal>

</goals> </execution> <execution> <id>report</id>

<phase>prepare-package</phase>

<goals> <goal>report</goal>

</goals> </execution> <execution> <id>post-unit-test</id>

<phase>test</phase>

<goals> <goal>report</goal>

</goals> <configuration>  <!-- Sets the path to the file which contains the execution data. -->

<dataFile>target/jacoco.exec</dataFile>

<!-- Sets the output directory for the code coverage report. -->

<outputDirectory>target/my-reports</outputDirectory>

</configuration> </execution> </executions> <configuration> <systemPropertyVariables> <jacoco-agent.destfile>target/jacoco.exec</jacoco-agent.destfile>

</systemPropertyVariables> </configuration> </plugin>

</plugins></build>

```

2- Run mvn clean install in your project directory.

## Installing Jenkins

1- Download Jenkins from their website: [https://jenkins.io/download/](https://jenkins.io/download/)

2- Follow the on screen steps to install Jenkins.

3- After you install it, you can access Jenkins Dashboard using the following URL:

[https://localhost:8080](https://localhost:8080)

## Installing SonarQube and SonarScanner

1- Download the SonarQube community edition from their website: [https://www.sonarqube.org/downloads/](https://www.sonarqube.org/downloads/)

2- install SonarScanner in your machine.

* if you are using Mac, run the following command:

```

brew install sonar-scanner

```

3- You can access SonarQube environment using this URL:

[https://localhost:9000](https://localhost:9000)

Use admin/admin for the credentials

## Integrating Github reposirty with Jenkins for automatic building

1- Install GitHub plugin in Jenkins

* Click on Manage Jenkins

* Click on Manage plugins

* Click on Available and search for GitHub Integration Plugin and install it

2- In your GitHub repository click on Settings then Webhook.

* Write in the URL for your Jenkins environment. Add /github-webhook/ to the end of it

[![Screen-Shot-2019-08-06-at-3-32-30-PM.png](https://i.postimg.cc/Dft5cnjX/Screen-Shot-2019-08-06-at-3-32-30-PM.png)](https://postimg.cc/Yjz1rKwr)

3- In your Jenkins environment, click on Manage Jenkins then Configure System

* Scroll down and add a GitHub server

[![Screen-Shot-2019-08-06-at-3-34-44-PM.png](https://i.postimg.cc/TYzShrMB/Screen-Shot-2019-08-06-at-3-34-44-PM.png)](https://postimg.cc/PPMKRvqM)

4- Create a freestyle project by clicking on new item

* In the Source Code Management, add the URL for your GitHub repository

[![Screen-Shot-2019-08-06-at-3-34-01-PM.png](https://i.postimg.cc/wMKf3pnL/Screen-Shot-2019-08-06-at-3-34-01-PM.png)](https://postimg.cc/mzwN60Lg)

5- Enable GitHub hook trigger in the Build Triggers section

[![Screen-Shot-2019-08-06-at-3-34-10-PM.png](https://i.postimg.cc/7Zb3vRWk/Screen-Shot-2019-08-06-at-3-34-10-PM.png)](https://postimg.cc/758G0K4s)

Now if you push anything to GitHub, Jenkins will automatically pull the changes and build the code.

## Integrating Jenkins with SonarQube

1- Install SonarQube Scanner plugin in Jenkins

* Click on manage Jenkins

* Click on manage plugins

* Search for SonarQube Scanner for Jenkins in the available section. Install the plugin

2- Configure SonarQube scanner in Jenkins

* Click of manage Jenkins

* Click on Global tool configuration

* Add SonarQube scanner

* Write a name for your scanner and specify the path to the scanner from your machine that we installed in previous steps

[![Screen-Shot-2019-08-06-at-3-47-02-PM.png](https://i.postimg.cc/GhbPKKwk/Screen-Shot-2019-08-06-at-3-47-02-PM.png)](https://postimg.cc/7bQTYgL6)

3- Create a new project in SonarQube environment. Fill in the required information.

4- Generate a token for your project and save it.

5- Configure SonarQube server in Jenkins

* Click on Manage Jenkins

* Click on Configure System

* Add a SonarQube server

[![Screen-Shot-2019-08-06-at-3-49-49-PM.png](https://i.postimg.cc/LsPvwsyf/Screen-Shot-2019-08-06-at-3-49-49-PM.png)](https://postimg.cc/068pJ912)

* Make sure to add the token you generated in creating your SonarQube project as a Secret Text

5- In your freestyle project, add SonarQube scanner as a build step

6- Fill in the analysis properties setting with your project information

```

sonar.projectKey=cucumbertest

sonar.projectName=cucumbertest

sonar.projectVersion=1.0

sonar.sources=.

sonar.tests=.

sonar.exclusions=**/*target*/**, **/*test*/**

sonar.test.inclusions=**/*test*/**

```

* Make sure to change the projectKey and projectName to your project information. Also, make sure to change other properties.

Now when you build your project, you will see SonarQube report.

[![Screen-Shot-2019-08-06-at-3-55-51-PM.png](https://i.postimg.cc/sDZcvxrL/Screen-Shot-2019-08-06-at-3-55-51-PM.png)](https://postimg.cc/YhMgPpjN)

## Integrating SonarQube with Jacoco in Jenkins to get code coverage results in SonarQube report

1- Add the following properties to your SonarQube analysis properties:

```

sonar.junit.reportsPath=./target/surefire-reports

sonar.surefire.reportsPath=./target/surefire-reports

sonar.jacoco.reportPath=./target/jacoco.exec

sonar.java.coveragePlugin=jacoco

```

Now when you build your project, you will see the code coverage in the SonarQube report

[![Screen-Shot-2019-08-06-at-3-56-30-PM.png](https://i.postimg.cc/13rY3X0Q/Screen-Shot-2019-08-06-at-3-56-30-PM.png)](https://postimg.cc/pmyB021G)

## Configuring quality gates based on SonarQube report to determine the status of the Jenkins build

If you want to fail the build based on SonarQube report, you could do so by configuring SonarQube and Jenkins to do that

1- In your SonarQube project, click on Quality Gates

* Click on create

* Choose a name

* Click on all and choose the project you want to apply this gate on

* Click on add condition and choose a metric.

[![Screen-Shot-2019-08-06-at-4-02-26-PM.png](https://i.postimg.cc/k4LBRnNJ/Screen-Shot-2019-08-06-at-4-02-26-PM.png)](https://postimg.cc/5YqxZWkT)

2- Install SonarQube gate plugin in Jenkins

3- Configure SonarQube quality gate

* Click on manage Jenkins

* Click on Configure System

* In the Quality gates - Sonar, add a Sonar instance

* Fill it with the required information

[![Screen-Shot-2019-08-06-at-4-04-18-PM.png](https://i.postimg.cc/hGjL9tmT/Screen-Shot-2019-08-06-at-4-04-18-PM.png)](https://postimg.cc/9D6w2VJQ)

4- Add a post build action to your Jenkins project. Choose Quality gates SonarQube plugin and write SonarQube project key.

[![Screen-Shot-2019-08-06-at-4-08-54-PM.png](https://i.postimg.cc/zXwDkwSY/Screen-Shot-2019-08-06-at-4-08-54-PM.png)](https://postimg.cc/0bQqkwBV)

Now when you build your project, if the quality criteria is not met, the build will fail.

## Sending Email and SMS notifications based on the build status of the project in Jenkins

Sending an email

1- Configure email notification in Jenkins

* Click on Manage Jenkins

* Click on Configure System

* Fill in the required information in the E-mail notification section

[![Screen-Shot-2019-08-06-at-4-11-53-PM.png](https://i.postimg.cc/hGvmJ4zw/Screen-Shot-2019-08-06-at-4-11-53-PM.png)](https://postimg.cc/Fftz84xg)

Note: You might need to reduce security in your gmail account

2- Add a post build step to your Jenkins project and choose Email notification

[![Screen-Shot-2019-08-06-at-4-10-36-PM.png](https://i.postimg.cc/TP6SGSKp/Screen-Shot-2019-08-06-at-4-10-36-PM.png)](https://postimg.cc/87ZwmZBS)

Sending an SMS:

1- Create an account with Twilio [https://www.twilio.com/](https://www.twilio.com/)

2- Purchase a phone number in Twilio. You will get an Account Sid and an Auth token, save them.

3- Install Twilio notifer plugin in Jenkins

4- Configure Twilio in Jenkins

* Click on Manage Jenkins

* Click on Configure System

* Fill in the required information for TwilioNotifier section using the information you got in step 2

[![Screen-Shot-2019-08-06-at-4-18-12-PM.png](https://i.postimg.cc/Ssr781Q1/Screen-Shot-2019-08-06-at-4-18-12-PM.png)](https://postimg.cc/LYJ1FT1j)

5- Add a post build step to your Jenkins project and choose Twilio Notifier. Fill in the fields

[![Screen-Shot-2019-08-06-at-4-10-48-PM.png](https://i.postimg.cc/PfDvBRng/Screen-Shot-2019-08-06-at-4-10-48-PM.png)](https://postimg.cc/K4ZYTQzf)

Now when you build your project, you will receive an email and an SMS.
