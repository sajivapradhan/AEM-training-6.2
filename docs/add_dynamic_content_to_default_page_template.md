# Add Dynamic Content to Default Page Template

Our default page template is a good start, but every page created from this template will be exactly the same.  Let's add some code to print out the Title of the page we are currently on.

1. Edit 'page-default.html'.

  Replace

  ```
  <title></title>
  ```

  with

  ```
  <title data-sly-test="${properties.jcr:title}">${properties.jcr:title}</title>
  ```

  Replace

  ```
  <p>
    Hello world! This is HTML5 Boilerplate.
  </p>
  ```

  with

  ```
  <p data-sly-test="${properties.jcr:title}">
    Welcome to the "${properties.jcr:title}" page!
  </p>
  ```

2. From the root of the digital project run the following maven command:

  ```
  mvn clean install -PautoInstallBundle -PautoInstallPackage
  ```

3. Open the following URL in your browser: [http://localhost:4502/content/digital.html] (http://localhost:4502/content/digital.html).  The webpage should resemble the following image:

  ![Screenshot](https://raw.githubusercontent.com/PRFTAdobe/AEMTraining/master/img/Screen%20Shot%202016-04-18%20at%203.57.17%20PM.png?token=ABVpFasWwhLEpe1063sHS0zxxesL_w5_ks5XHnbgwA%3D%3D "Screenshot")

## Adding a Sightly Use Class

Let's add some more logic to our page title and add some dynamic metadata to our html.  For this we will need a Sightly Use Class.  A use-class should only be used when something cannot be done in Sightly alone.

1. Edit the pom.xml file under the root digital directory.  Add the following dependencies:

  ```xml
  <dependency>
    <groupId>com.adobe.aem</groupId>
    <artifactId>uber-jar</artifactId>
    <version>6.2.0</version>
    <classifier>obfuscated-apis</classifier>
    <scope>provided</scope>
  </dependency>
  <dependency>
    <groupId>org.powermock</groupId>
    <artifactId>powermock-api-mockito</artifactId>
    <version>1.6.3</version>
    <scope>test</scope>
  </dependency>
  <dependency>
    <groupId>org.powermock</groupId>
    <artifactId>powermock-module-junit4</artifactId>
    <version>1.6.3</version>
    <scope>test</scope>
  </dependency>
  ```

2. Edit the pom.xml file under the `digital/core` directory.  Add the following dependencies:

  ```xml
  <dependency>
    <groupId>com.adobe.aem</groupId>
    <artifactId>uber-jar</artifactId>
    <classifier>obfuscated-apis</classifier>
  </dependency>
  <dependency>
    <groupId>org.powermock</groupId>
    <artifactId>powermock-api-mockito</artifactId>
  </dependency>
  <dependency>
    <groupId>org.powermock</groupId>
    <artifactId>powermock-module-junit4</artifactId>
  </dependency>
  ```

3. We are going to follow TDD (Test Driven Design).  Let's create a test class named "PageHelperPageTitleTest" under the package `com.perficient.adobe.digital.core.sightly` in the `digital/core/src/test/java` directory.

4. Set the contents of the class to be the following:

  ```java
  package com.perficient.adobe.digital.core.sightly;

  import org.junit.runner.RunWith;
  import org.powermock.core.classloader.annotations.PrepareForTest;
  import org.powermock.modules.junit4.PowerMockRunner;

  @RunWith(PowerMockRunner.class)
  @PrepareForTest({ PageHelper.class })
  public class PageHelperPageTitleTest {

  }
  ```
