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
      categories="[digital]"/>
  ```

  > Setting the Primary Type to "cq:ClientLibraryFolder" designates the "clientlib" directory as a Client-side Library Folder, which allow you to store the client-side code in the repository, organize it into categories, and define when and how each category of code is to be served to the client.

18. Create the following folders under clientlib: js, css, and img.
19. Create the following files under clientlib: js.txt and css.txt.
20. Create a folder under the clientlib/js named "vendor".
21. Copy `jquery-1.12.0.min.js` and `modernizr-2.8.3.min.js` from the js/vendor folder within the Boilerplate archive into the newly created vendor directory.
22. Replace the jQuery references within page-default.html with the following code:

  Replace

  ```
    <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
    <script>
      window.jQuery||document.write('<script src="js/vendor/jquery-1.12.0.min.js"><\/script>')
    </script>
  ```

  with

  ```
    <script src="/etc/designs/digital/clientlib/js/vendor/jquery-1.12.0.min.js"></script>
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

28.  Replace the js references within page-default.html with the following code:

  Replace

  ```
  <script src="js/plugins.js"></script>
  <script src="js/main.js"></script>
  ```

  with

  ```
  <script data-sly-use.clientLib="${'/libs/granite/sightly/templates/clientlib.html'}" data-sly-call="${clientLib.js @ categories='digital'}" data-sly-unwrap></script>
  ```

29.  Replace the stylesheet references within page-default.html with the following code:

  Replace

  ```
  <link rel="stylesheet" href="css/normalize.css">
  <link rel="stylesheet" href="css/main.css">
  ```

  with

  ```
  <link rel='stylesheet' data-sly-use.clientLib="${'/libs/granite/sightly/templates/clientlib.html'}" data-sly-call="${clientLib.css @ categories='digital'}" data-sly-unwrap />
  ```
