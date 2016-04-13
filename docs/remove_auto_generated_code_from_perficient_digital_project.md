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
