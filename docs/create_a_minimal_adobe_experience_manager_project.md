# Create a minimal Adobe Experience Manager project

Type the following in a terminal or in a command prompt:

```shell
mvn archetype:generate \
-DarchetypeGroupId=com.adobe.granite.archetypes \
-DarchetypeArtifactId=aem-project-archetype \
-DarchetypeVersion=10 \
-DarchetypeRepository=https://repo.adobe.com/nexus/content/groups/public/ \
-DgroupId=com.perficient.adobe \
-DartifactId=digital \
-Dversion=1.0-SNAPSHOT \
-Dpackage=com.perficient.adobe.digital \
-DappsFolderName=perficientdigital \
-DartifactName="Perficient Digital" \
-DcomponentGroupName="Perficient Digital Components" \
-DcontentFolderName=perficientdigital \
-DcssId=digital \
-DpackageGroup="Perficient Digital Content Package" \
-DsiteName="Perficient Digital Site" -batch-mode
```