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
