# Import Perficient Digital Project into an IDE and Install AEM Developer Tools

## Import into Eclipse

1. Choose File -> Import -> Existing Maven Projects.

  ![alt text](https://raw.githubusercontent.com/PRFTAdobe/AEMTraining/master/img/Screen%20Shot%202016-04-13%20at%204.53.45%20PM.png?token=ABVpFZZPn7Nw3Mkf433Tvgn9_bX-IPy-ks5XF_E_wA%3D%3D "screenshot")

2. Select the Maven Project and click Finish.

  ![alt text](https://raw.githubusercontent.com/PRFTAdobe/AEMTraining/master/img/Screen%20Shot%202016-04-13%20at%204.58.12%20PM.png?token=ABVpFZQQ6YzzjfmcmnSPGA6qDyvSxpK3ks5XF_F1wA%3D%3D "screenshot")

## Import into IntelliJ

1. If no project is currently open in IntelliJ IDEA, click Import Project on the Welcome screen. Otherwise, select File | New | Project from Existing Sources.

2. In the dialog that opens, select the directory that contains the project to be imported, or a file that contains an appropriate project description. Click OK.

3. On the first page of the Import Project wizard, select Maven, and click Next. (This page is not shown if IntelliJ IDEA has guessed what you are importing.)

4. On the next page of the wizard, specify the project root directory. Check the "Search for projects recursively" and "Import Maven projects automatically" options. Click Next.

5. On the next page of the wizard, select all profiles, and click Next.

6. On the next page of the wizard, select the projects to be imported. Note that IntelliJ IDEA detects Maven projects by scanning the specified root. Click Next.

7. On the next page of the wizard, select the project's SDK. Click Next.

8. On the next page of the wizard, specify the new project name and location of the project files. Click Finish.

## Install AEM Developer Tools for Eclipse

1. Browse the [AEM Developer Tools Web Site] (https://eclipse.adobe.com/aem/dev-tools/).

2. Copy the Installation Link.

3. In Eclipse, open the Help menu.

4. Click Install New Software.

5. Click Add....

6. In Name type AEM Developer Tools.

7. In Location copy the installation URL.

8. Click Ok.

9. Check both AEM and Sling plugins.

10. Click Next.

11. Click Next.

12. Accept the license agreements and click Finish.

13. Click Yes in order to restart Eclipse.

## Enable Remote Debugging within AEM

1. With your favorite Text Editor, open <aem-install>/cq-quickstart/bin/start if your are on a Unix-based Operating System (Linux, Solaris and Mac OS X) or <aem-install>/cq-quickstart/bin/start.bat if you are on Windows.

2. Add "-agentlib:jdwp=transport=dt_socket,address=2979,server=y,suspend=n" after "java $CQ_JVM_OPTS" and before "-jar $CURR_DIR/$CQ_JARFILE" if your are on a Unix-based Operating System (Linux, Solaris and Mac OS X).  Add "-agentlib:jdwp=transport=dt_socket,address=2979,server=y,suspend=n" after "java %CQ_JVM_OPTS%" and before "-jar %CurrDirName%\%CQ_JARFILE%" if you are on Windows.

3.  You will start AEM with the start or start.bat batch files instead of the cq-quickstart jar.

## Define a new AEM Server from within Eclipse

1. Choose File -> New -> Other -> Server.

2. Select "Adobe Experience Manager" as the server type and click Next.

3. Click Finish.

4. Switch to the Java EE workspace within Eclipse.

5. Click on the Servers tab.

6. Double-click on "Adobe Experience Manager at localhost".

7. Change the Port to 4502 and the Debug Port to 2979.

8. Save your changes.

## Install AEM Developer Tools for IntelliJ

AEM Developer Tools for IntelliJ is still in Beta.  Download the Plugin here: https://github.com/headwirecom/aem-ide-tooling-4-intellij/wiki and follow the instructions here: https://github.com/headwirecom/aem-ide-tooling-4-intellij/blob/master/documenation/AEM%20Tooling%20Plugin%20for%20IntelliJ%20IDEA.md.  Use 2979 for the Debug Port. 
