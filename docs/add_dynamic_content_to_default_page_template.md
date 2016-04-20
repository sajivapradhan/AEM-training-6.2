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

Let's make our template a little more interesting.  Look at all the possible page properties: https://docs.adobe.com/docs/en/aem/6-1/author/page-authoring/editing-page-properties.html.  A page's tile should be referenced by the "Page Title" property, however this field is not mandatory.  The "Title" property is mandatory, but this restriction is sidestepped when a page is created via a batch process.  A page must always contain the "Name" property.  We need code to output the value of the "Page Title" property.  If it is blank, our code should return the value of the "Title" property.  If this property is undefined as well, our code should fall back to the "Name" property. For this we will need a Sightly Use Class.  A use-class should only be used when something cannot be done in Sightly alone.

1. Edit the pom.xml file under the root digital directory.  Add the following dependencies:

  ```xml
  <dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-log4j12</artifactId>
    <version>1.7.13</version>
    <scope>test</scope>
  </dependency>
  <dependency>
	  <groupId>org.apache.commons</groupId>
	  <artifactId>commons-lang3</artifactId>
	  <version>3.3.2</version>
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

2. Update the version references in the pom.xml for "mockito-all" to 1.10.19.  Update the version reference in the pom.xml for all depenedencies of groupId "org.slf4j" to 1.7.13 from 1.5.11.

3. Edit the pom.xml file under the `digital/core` directory.  Add the following dependencies:

  ```xml
  <dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-log4j12</artifactId>
    <version>1.7.13</version>
    <scope>test</scope>
  </dependency>
  <dependency>
	  <groupId>org.apache.commons</groupId>
	  <artifactId>commons-lang3</artifactId>
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

4. We are going to follow TDD (Test Driven Design).  More information on TDD can be found here: https://en.wikipedia.org/wiki/Test-driven_development.  Let's create a test class named "PageHelperPageTitleTest" under the package `com.perficient.adobe.digital.core.sightly` in the `digital/core/src/test/java` directory.  Set the contents of the class to be the following:

  ```java
  package com.perficient.adobe.digital.core.sightly;

  import org.junit.runner.RunWith;
  import org.mockito.Spy;
  import org.powermock.core.classloader.annotations.PrepareForTest;
  import org.powermock.modules.junit4.PowerMockRunner;

  @RunWith(PowerMockRunner.class)
  @PrepareForTest({ PageHelper.class })
  public class PageHelperPageTitleTest {

    @Spy
	  private PageHelper pageHelper = new PageHelper();

  }
  ```

5.  Your IDE should be informing you the PageHelper class cannot be resolved to a type.  Create a class named "PageHelper" under the package `com.perficient.adobe.digital.core.sightly` in the `digital/core/src/main/java` directory.  I usually like to name my Sightly Use Classes the name of the component they adding functionality to and the word "Helper".  If I created a Sightly Use Class to add functionality to an accordion component, I would name the class "AccordionHelper" and so on.  Set the contents of the class to be the following:

  ```java
  package com.perficient.adobe.digital.core.sightly;

  import com.adobe.cq.sightly.WCMUse;

  public class PageHelper extends WCMUse {

	  @Override
	  public void activate() throws Exception {

	  }

  }
  ```

6.  Let's add three methods to our test class to handle all the possible sources for our page title.

  ```java
  /**
  * Test retrieving the title when the "Page Title" property has been set.
  *
  * @throws Exception
  *             the exception
  */
  @Test
  public void testTitleWithPageTitle() throws Exception {		
    pageHelper.activate();
    assertEquals("Perficient Digital", pageHelper.getPageTitle());
  }

  /**
  * Test retrieving the title when the "Title" property has been set and the "Page Title" property has not been set.
  *
  * @throws Exception
  *             the exception
  */
  @Test
  public void testTitleWithTitle() throws Exception {
    pageHelper.activate();
		assertEquals("Digital", pageHelper.getPageTitle());
  }

  /**
   * Test retrieving the title when only the page name is present.
   *
   * @throws Exception
   *             the exception
   */
  @Test
  public void testTitleWithName() throws Exception {
    pageHelper.activate();
    assertEquals("digital", pageHelper.getPageTitle());
  }
  ```

7.  Let's add the method getPageTitle to the PageHelper class.

  ```java
  /**
	 * Gets the page title.
	 *
	 * @return the page title
	 */
	public String getPageTitle() {
		String pageTitle = currentPage.getName();
		if (StringUtils.isNotEmpty(currentPage.getTitle())) {
			pageTitle = currentPage.getTitle();
		}
		if (StringUtils.isNotEmpty(currentPage.getPageTitle())) {
			pageTitle = currentPage.getPageTitle();
		}
		return pageTitle;
	}
  ```

8. Set `currentPage` in the activate function.

  ```java
  /** The current page. */
	  private Page currentPage;
	/*
	 * (non-Javadoc)
	 *
	 * @see com.adobe.cq.sightly.WCMUsePojo#activate()
	 */
	@Override
	public void activate() throws Exception {
		currentPage = getCurrentPage();
	}

  ```

9. Use Mockito to "mock" the responses from AEM.

  ```java
  /**
  * Test retrieving the title when the "Page Title" property has been set.
  *
  * @throws Exception
  *             the exception
  */
  @Test
  public void testTitleWithPageTitle() throws Exception {		
    pageHelper.activate();
    when(currentPage.getTitle()).thenReturn("Digital");
    when(currentPage.getPageTitle()).thenReturn("Perficient Digital");
    assertEquals("Perficient Digital", pageHelper.getPageTitle());
  }

  /**
  * Test retrieving the title when the "Title" property has been set and the "Page Title" property has not been set.
  *
  * @throws Exception
  *             the exception
  */
  @Test
  public void testTitleWithTitle() throws Exception {
    pageHelper.activate();
		when(currentPage.getTitle()).thenReturn("Digital");
		assertEquals("Digital", pageHelper.getPageTitle());
  }

  /**
   * Test retrieving the title when only the page name is present.
   *
   * @throws Exception
   *             the exception
   */
  @Test
  public void testTitleWithName() throws Exception {
    pageHelper.activate();
    assertEquals("digital", pageHelper.getPageTitle());
  }
  ```

10.  Run the JUnit tests.  You should not see any failed tests.

11.  "Use" the Sightly Use Class within page-default.html.  Update the HTML tag to include the data-sly-use attribute:

  ```html
  <html class="no-js" lang="" data-sly-use.pageHelper="com.perficient.adobe.digital.core.sightly.PageHelper">
  ```

  We are assigning the Sightly Use Class "PageHelper" to the variable pageHelper.

12.  Update the title element and paragraph tag to use this variable.

  ```html
  <title>${pageHelper.pageTitle}</title>
  ```

  and

  ```html
  <p>
    Welcome to the "${pageHelper.pageTitle}" page!
  </p>
  ```

13. Open up your browser to [http://localhost:4502/siteadmin#/content] (http://localhost:4502/siteadmin#/content).

14. Right-click on the "Digital" page and click on "Properties".  Expand "More Titles and Description".  Set "Page Title" to "Perficient Digital" and click OK.

15.  At the root of digital, type the following in a terminal or in a command prompt:

  ```
  mvn clean install -PautoInstallBundle -PautoInstallPackage
  ```

16. Open the following URL in your browser: [http://localhost:4502/content/digital.html] (http://localhost:4502/content/digital.html).  The webpage should resemble the following image:

  ![Screenshot](https://raw.githubusercontent.com/PRFTAdobe/AEMTraining/master/img/Screen%20Shot%202016-04-20%20at%203.41.35%20PM.png?token=ABVpFeOl7W0eTcAPou6s9LDjypZE2x51ks5XIRZHwA%3D%3D "Screenshot")
