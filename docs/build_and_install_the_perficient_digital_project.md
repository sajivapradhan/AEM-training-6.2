# Build and Install the Perficient Digital Project

## Create the "Perficient Digital" Project within CRXDE Lite:

1. Open CRXDE Lite in your browser.
  [http://localhost:4502/crx/de](http://localhost:4502/crx/de/index.jsp)
2. Click on the button containing the text "anonymous@crx.default" and login to the application using the default credentials admin/admin.
3. In the Navigation pane, select Create ..., then Create Project ....
4. Define:
  * Project Name - "digital"
  * Java Package - "com.perficient.adobe"
5. Click Create
6. Click Save All to save the changes on the server.

## Build and Install the "Perficient Digital" Project

1. Change the current working directory to the root of the digital project.
2. Type the following in a terminal or in a command prompt:

  ```
  mvn help:all-profiles
  ```
  This command will display a list of available profiles under the current project.

  We are interested in the ```autoInstallPackage``` and the ```autoInstallBundle``` profiles.

  The ```autoInstallBundle``` profile creates an OSGI bundle from the Java code and installs the bundle into the AEM OSGI container.

  The ```autoInstallPackage``` profile creates an archive containing application and content data and installs the package into the AEM repository.

3. Type the following in a terminal or in a command prompt:

  ```
  mvn clean install -PautoInstallBundle -PautoInstallPackage
  ```
4. Verify the bundle and content packages were installed correctly by navigating to [http://localhost:4502/content/digital/en.html] (http://localhost:4502/content/digital/en.html).

  You should see a page looking similar to the following screenshot:
