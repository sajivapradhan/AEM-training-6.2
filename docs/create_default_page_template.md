# Create Default Page Template

1. From within the ui.apps project within digital, navigate to the templates directory:
    ```
    cd ./src/main/content/jcr_root/apps/digital/templates
    ```
2. Create a new folder named "page-default".
3. Create a node definition file within "page-default" named ".content.xml".
4. Set the contents of the file to the following:

  ```
  <?xml version="1.0" encoding="UTF-8"?>
  <jcr:root xmlns:sling="http://sling.apache.org/jcr/sling/1.0" xmlns:cq="http://www.day.com/jcr/cq/1.0" xmlns:jcr="http://www.jcp.org/jcr/1.0"
      jcr:description="Default Template for Perficient Digital."
      jcr:primaryType="cq:Template"
      jcr:title="Perficient Digital Default Template"
      allowedPaths="[/content(/.*)?]"
      ranking="{Long}1">
      <jcr:content
          jcr:primaryType="cq:PageContent"
          sling:resourceType="digital/components/structure/page-default">
      </jcr:content>
  </jcr:root>
  ```

  > **The Anatomy of a template**
  > - **Description**: The description that is assigned to the template.
  > - **Primary Type**: Sets the Node to be of type "cq:Template".
  > - **Title**: The title that is assigned to the template.
  > - **Allowed Paths**: /content(/.\*)? means that we can access our template, anywhere placed inside content folder
  > - **Ranking**: The order (ascending) in which this template will appear in relation to other templates. Setting this value to 1 ensures that the template appears first in the list.
  > - **Resource Type**: The component referenced by our template.

5. Navigate to the structure directory:
  ```
  cd ./src/main/content/jcr_root/apps/digital/components/structure
  ```
6. Create a new folder named "page-default".
7. Create a node definition file within "page-default" named ".content.xml".
8. Set the contents of the file to the following:

  ```
  <?xml version="1.0" encoding="UTF-8"?>
  <jcr:root xmlns:sling="http://sling.apache.org/jcr/sling/1.0" xmlns:cq="http://www.day.com/jcr/cq/1.0" xmlns:jcr="http://www.jcp.org/jcr/1.0"
      jcr:primaryType="cq:Component"
      jcr:title="Perficient Digital Default Page"
      sling:resourceSuperType="foundation/components/page"
      componentGroup=".hidden"/>
  ```

9. Create a file named "page-default.html" within the page-default directory.
10. Copy the contents of index.html from the Boilerplate archive into page-default.html.
11. Remove the analytics code.
12. Navigate to the digital directory under designs:

  ```
  cd ./src/main/content/jcr_root/etc/designs/digital/
  ```

13. Create a node definition file within "digital" named ".content.xml".
14. Set the contents of the file to the following:

  ```
  <?xml version="1.0" encoding="UTF-8"?>
  <jcr:root xmlns:sling="http://sling.apache.org/jcr/sling/1.0" xmlns:cq="http://www.day.com/jcr/cq/1.0" xmlns:jcr="http://www.jcp.org/jcr/1.0" xmlns:nt="http://www.jcp.org/jcr/nt/1.0"
      jcr:primaryType="cq:Page">
      <jcr:content
          jcr:primaryType="nt:unstructured"
          jcr:title="Perficient Digital Design"
          sling:resourceType="wcm/core/components/designer">
      </jcr:content>
  </jcr:root>
  ```
  > Setting the Resource Type to "wcm/core/components/deigner" delegates this directory and the contents within it as responsible for the "design" of the site (js, css, images, and etc ...)

15. Create a folder named "clientlib" under /etc/designs/digital.
16. Create a node definition file within "clientlib" named ".content.xml".
17. Set the contents of the file to the following:

  ```
  <?xml version="1.0" encoding="UTF-8"?>
  <jcr:root xmlns:cq="http://www.day.com/jcr/cq/1.0" xmlns:jcr="http://www.jcp.org/jcr/1.0"
      jcr:primaryType="cq:ClientLibraryFolder"
      categories="[digital]"      
      dependencies="[cq.jquery]/>
  ```

  > Setting the Primary Type to "cq:ClientLibraryFolder" designates the "clientlib" directory as a Client-side Library Folder, which allow you to store the client-side code in the repository, organize it into categories, and define when and how each category of code is to be served to the client.  The “clientlib” functionality will manage all your Javascript and CSS resources in your application. It takes cares of dependency management, merging files and minifying content.

18. Create the following folders under clientlib: js, css, and img.
19. Create the following files under clientlib: js.txt and css.txt.
20. Create a folder under the clientlib/js named "vendor".
21. Copy `modernizr-2.8.3.min.js` from the js/vendor folder within the Boilerplate archive into the newly created vendor directory.
22. Delete the jQuery references within page-default.html.  The property `dependencies="[cq.jquery]` in the node definition file informs AEM of the dependency upon jQuery.  AEM will handle the insertion of the jQuery library within the code.

  Remove

  ```
    <script>
      window.jQuery||document.write('<script src="js/vendor/jquery-1.12.0.min.js"><\/script>')
    </script>
  ```

23. Replace the Modernizr reference within page-default.html with the following code:

  Replace

  ```
  <script src="js/vendor/modernizr-2.8.3.min.js"></script>
  ```

  with

  ```
  <script src="/etc/designs/digital/clientlib/js/vendor/modernizr-2.8.3.min.js"></script>
  ```

24. Copy `main.js` and `plugins.js` from the js folder within the Boilerplate archive into the js folder under clientlib.
25. Copy `main.css` and `normalize.css` from the css folder within the Boilerplate archive into the css folder under clientlib.
26. Edit js.txt to include the following:

  ```
  #base=js
  plugins.js
  main.jss
  ```

27. Edit css.txt to include the following:

  ```
  #base=css
  normalize.css
  main.css
  ```

28.  Replace the js references within page-default.html with the following code.  We will use Sightly's data-sly-call attribute to invoke the JavaScript clientlib.

  Replace

  ```
  <script src="js/plugins.js"></script>
  <script src="js/main.js"></script>
  ```

  with

  ```
  <script data-sly-use.clientLib="${'/libs/granite/sightly/templates/clientlib.html'}" data-sly-call="${clientLib.js @ categories='digital'}" data-sly-unwrap></script>
  ```

29.  Replace the stylesheet references within page-default.html with the following code.  We will use Sightly's data-sly-call attribute to invoke the Stylesheet clientlib.

  Replace

  ```
  <link rel="stylesheet" href="css/normalize.css">
  <link rel="stylesheet" href="css/main.css">
  ```

  with

  ```
  <link rel='stylesheet' data-sly-use.clientLib="${'/libs/granite/sightly/templates/clientlib.html'}" data-sly-call="${clientLib.css @ categories='digital'}" data-sly-unwrap />
  ```

30. Create favicon folder within `clientlib/img` directory.
31. Right-click on the following image and save it to you computer.

  ![Perficient Logo](https://raw.githubusercontent.com/PRFTAdobe/AEMTraining/master/img/PerficientLogo.png?token=ABVpFQRkeiXGG99AiNpDQNL7tU8IrEkpks5XHiJTwA%3D%3D "logo")

32. Open the following URL in your browser: http://www.favicon-generator.org/.  Choose the image you just downloaded and click "Create Favicon".

33. Download the generated favicon.

34. Copy the contents of the favicon archive to the favicon folder you created above.

35. Replace the favicon references within page-default.html with the following code:

  Replace

  ```
  <link rel="apple-touch-icon" href="apple-touch-icon.png">
  <!-- Place favicon.ico in the root directory -->
  ```

  with

  ```
  <link rel="shortcut icon" href="/etc/designs/digital/clientlib/img/favicon/favicon.ico" type="image/x-icon">
  <link rel="icon" href="/etc/designs/digital/clientlib/img/favicon/favicon.ico" type="image/x-icon">
  <link rel="apple-touch-icon" sizes="57x57" href="/etc/designs/digital/clientlib/img/favicon/apple-icon-57x57.png">
  <link rel="apple-touch-icon" sizes="60x60" href="/etc/designs/digital/clientlib/img/favicon/apple-icon-60x60.png">
  <link rel="apple-touch-icon" sizes="72x72" href="/etc/designs/digital/clientlib/img/favicon/apple-icon-72x72.png">
  <link rel="apple-touch-icon" sizes="76x76" href="/etc/designs/digital/clientlib/img/favicon/apple-icon-76x76.png">
  <link rel="apple-touch-icon" sizes="114x114" href="/etc/designs/digital/clientlib/img/favicon/apple-icon-114x114.png">
  <link rel="apple-touch-icon" sizes="120x120" href="/etc/designs/digital/clientlib/img/favicon/apple-icon-120x120.png">
  <link rel="apple-touch-icon" sizes="144x144" href="/etc/designs/digital/clientlib/img/favicon/apple-icon-144x144.png">
  <link rel="apple-touch-icon" sizes="152x152" href="/etc/designs/digital/clientlib/img/favicon/apple-icon-152x152.png">
  <link rel="apple-touch-icon" sizes="180x180" href="/etc/designs/digital/clientlib/img/favicon/apple-icon-180x180.png">
  <link rel="icon" type="image/png" sizes="192x192"  href="/etc/designs/digital/clientlib/img/favicon/android-icon-192x192.png">
  <link rel="icon" type="image/png" sizes="32x32" href="/etc/designs/digital/clientlib/img/favicon/favicon-32x32.png">
  <link rel="icon" type="image/png" sizes="96x96" href="/etc/designs/digital/clientlib/img/favicon/favicon-96x96.png">
  <link rel="icon" type="image/png" sizes="16x16" href="/etc/designs/digital/clientlib/img/favicon/favicon-16x16.png">
  <link rel="manifest" href="/etc/designs/digital/clientlib/img/favicon/manifest.json">
  <meta name="msapplication-TileColor" content="#ffffff">
  <meta name="msapplication-TileImage" content="/etc/designs/digital/clientlib/img/favicon/ms-icon-144x144.png">
  <meta name="theme-color" content="#ffffff">
  ```

36. Open the following URL in your browser: [http://localhost:4502/system/console/configMgr] (http://localhost:4502/system/console/configMgr).

37. Edit the "Day CQ HTML Library Manager" configuration and check the Minify and Gzip options.  Save the configuration.

38.  From the root of the digital project run the following maven command:

  ```
  mvn clean install -PautoInstallBundle -PautoInstallPackage
  ```

39.  Open the following URL in your browser: [http://localhost:4502/siteadmin] (http://localhost:4502/siteadmin).

40.  Click on "Websites" folder icon in the Left Navigation Panel.  Click New -> New Page.  Enter "Digital" as the Title.  Ensure "Perficient Digital Default Template" is selected and click "Create".

  ![Screenshot](https://raw.githubusercontent.com/PRFTAdobe/AEMTraining/master/img/Screen%20Shot%202016-04-18%20at%2010.25.59%20AM.png?token=ABVpFVrovIaYL8JZ1WLU61p3SwJydQjEks5XHizewA%3D%3D "Screenshot")

41. Open the following URL in your browser: [http://localhost:4502/content/digital.html] (http://localhost:4502/content/digital.html).  The webpage should resemble the following image:

  ![Screenshot](https://raw.githubusercontent.com/PRFTAdobe/AEMTraining/master/img/Screen%20Shot%202016-04-18%20at%2010.40.25%20AM.png?token=ABVpFUNR5FymwfHyAUkilmvMLmBhmyf0ks5XHi0NwA%3D%3D "Screenshot")

  You will notice the favicon displayed in the tab within your browser.

42.  View the source of the page.  AEM has inserted references to the jQuery library within your code.  You will also see that AEM has combined and minified your CSS & JS files.

  See [http://localhost:4502/etc/designs/digital/clientlib.min.js] (http://localhost:4502/etc/designs/digital/clientlib.min.js) and [http://localhost:4502/etc/designs/digital/clientlib.min.css] (http://localhost:4502/etc/designs/digital/clientlib.min.css)
