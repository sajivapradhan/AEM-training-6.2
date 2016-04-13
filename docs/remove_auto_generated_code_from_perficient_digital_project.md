# Remove auto-generated code from Perficient Digital Project

The AEM Project Archetype provides an excellent starting point when developing our own projects.  The sample code demonstrates many of the features (such as Sightly and Sling Models) introduced in AEM 6.0.  It is worth review the code and configurations.  For more information about archetype see the Git Hub page [https://github.com/Adobe-Marketing-Cloud/aem-project-archetype] (https://github.com/Adobe-Marketing-Cloud/aem-project-archetype).

For the purposes of this tutorial, we will remove much of the auto-generated code.  We will be adding some of the code back in as the tutorial progresses.  We will also be creating all new code to meet our particular use-case, an implementation of the Perficient Digital design.

## Remove Site Content

1. Open CRXDE Lite in your browser.
  [http://localhost:4502/crx/de](http://localhost:4502/crx/de/index.jsp)
2. Click on the button containing the text "anonymous@crx.default" and login to the application using the default credentials admin/admin.
3. Navigate to /content/digital and press the "Delete" button.

   ![alt text](https://raw.githubusercontent.com/PRFTAdobe/AEMTraining/master/img/Screen%20Shot%202016-04-13%20at%2012.23.00%20PM.png?token=ABVpFagzV_5n8xTdgwrBKnG3W0H8DcJzks5XF620wA%3D%3D "screenshot")
4. Click the "Save All" button.

## Remove Local Content

1. Remove the ```it.launcher```, ```it.tests```, and ```ui.content``` projects from the root of ```digital```
2. Edit the digital/pom.xml.
3. From the <modules> section remove ui.content, it.tests, and it.launcher
    <modules>
        <module>core</module>
        <module>ui.apps</module>
        <module>ui.content</module> <-- Remove
        <module>it.tests</module> <-- Remove
        <module>it.launcher</module> <-- Remove
    </modules>
4. From within the ```core``` project, remove everything under the following directory ```./src/main/java/com/perficient/adobe/digital/core```.
5. From within the ```core``` project, remove everything under the following directory ```./src/test/java/com/perficient/adobe/digital/core```.
6. From within the ```ui.apps``` project, remove everything under the following directories:

  ```
  ./src/main/content/jcr_root/apps/digital/components/content
  ./src/main/content/jcr_root/etc/designs/digital
  ./src/main/content/jcr_root/apps/digital/tests
  ```

7. From within the ```ui.apps``` project, remove the following directories:

 ```
 ./src/main/content/jcr_root/apps/digital/i18n
 ./src/main/content/jcr_root/apps/digital/config
 ./src/main/content/jcr_root/apps/digital/config.author
 ./src/main/content/jcr_root/apps/digital/components/structure/logo
 ./src/main/content/jcr_root/apps/digital/components/structure/page
 ./src/main/content/jcr_root/apps/digital/components/structure/topnav
 ./src/main/content/jcr_root/apps/digital/templates/page-content
 ./src/main/content/jcr_root/apps/digital/templates/page-home
 ```

7. Run ```mvn clean``` to clean your project.
8. Your directory listing should resemble the following:

  ```
  ./core
  ./core/.classpath
  ./core/pom.xml
  ./core/src
  ./core/src/main
  ./core/src/main/java
  ./core/src/main/java/com
  ./core/src/main/java/com/perficient
  ./core/src/main/java/com/perficient/adobe
  ./core/src/main/java/com/perficient/adobe/digital
  ./core/src/main/java/com/perficient/adobe/digital/core
  ./core/src/test
  ./core/src/test/java
  ./core/src/test/java/com
  ./core/src/test/java/com/perficient
  ./core/src/test/java/com/perficient/adobe
  ./core/src/test/java/com/perficient/adobe/digital
  ./core/src/test/java/com/perficient/adobe/digital/core
  ./pom.xml
  ./README.md
  ./ui.apps
  ./ui.apps/.classpath
  ./ui.apps/pom.xml
  ./ui.apps/src
  ./ui.apps/src/main
  ./ui.apps/src/main/content
  ./ui.apps/src/main/content/jcr_root
  ./ui.apps/src/main/content/jcr_root/apps
  ./ui.apps/src/main/content/jcr_root/apps/.content.xml
  ./ui.apps/src/main/content/jcr_root/apps/digital
  ./ui.apps/src/main/content/jcr_root/apps/digital/components
  ./ui.apps/src/main/content/jcr_root/apps/digital/components/content
  ./ui.apps/src/main/content/jcr_root/apps/digital/components/structure
  ./ui.apps/src/main/content/jcr_root/apps/digital/templates
  ./ui.apps/src/main/content/jcr_root/apps/digital/tests
  ./ui.apps/src/main/content/jcr_root/apps/sling
  ./ui.apps/src/main/content/jcr_root/apps/sling/servlet
  ./ui.apps/src/main/content/jcr_root/apps/sling/servlet/errorhandler
  ./ui.apps/src/main/content/jcr_root/apps/sling/servlet/errorhandler/404.html
  ./ui.apps/src/main/content/jcr_root/apps/sling/servlet/errorhandler/ResponseStatus.java
  ./ui.apps/src/main/content/jcr_root/etc
  ./ui.apps/src/main/content/jcr_root/etc/designs
  ./ui.apps/src/main/content/jcr_root/etc/designs/digital
  ./ui.apps/src/main/content/jcr_root/META-INF
  ./ui.apps/src/main/content/jcr_root/META-INF/MANIFEST.MF
  ./ui.apps/src/main/content/jcr_root/WEB-INF
  ./ui.apps/src/main/content/jcr_root/WEB-INF/lib
  ./ui.apps/src/main/content/jcr_root/WEB-INF/web.xml
  ./ui.apps/src/main/content/META-INF
  ./ui.apps/src/main/content/META-INF/vault
  ./ui.apps/src/main/content/META-INF/vault/config.xml
  ./ui.apps/src/main/content/META-INF/vault/filter.xml
  ./ui.apps/src/test
  ```

  Source code can be downloaded here: https://github.com/PRFTAdobe/AEMTraining/raw/master/src/Remove%20auto-generated%20code%20from%20Perficient%20Digital%20Project/digital.zip
