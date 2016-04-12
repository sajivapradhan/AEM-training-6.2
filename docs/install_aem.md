# Installing Adobe Experience Manager 6.1

Create an install directory on your local machine. On a unix machine this might be

```shell
    /opt/aem
```

In Windows it might be

```shell
    C:\Program Files\aem
```

Equally, it is common to install sample instances in a folder right on the desktop. In any case we will refer to this location generically as

```shell
    <aem-install>
```

Note that path of the file directory must consist of only US ASCII characters.

Place the the jar and license files in this directory:

```shell
<aem-install>/
    cq-quickstart-6.1.0.jar
    license.properties
```

If you do not provide a license.properties file, AEM will redirect your browser to a Welcome screen on startup, where you can enter a license key. You will need to request a valid license key from Adobe if you do not yet have one.

To start up the instance in a GUI environment, just double-click the cq-quickstart-6.1.0.jar file.

Alternative, you can launch AEM from the command line. For a 32-bit Java VM enter the following:

```shell
java -Xmx1024M -jar cq-quickstart-6.1.0.jar
```

For a 64-bit VM, enter:

```shell
java -XX:MaxPermSize=256m -Xmx1024M -jar cq-quickstart-6.1.0.jar
```


AEM will take a few minutes to unpack the jar file, install itself, and start up. The above procedure results in:

* an AEM author instance
* running on localhost
* at port 4502

To access the instance point your browser to:

http://localhost:4502
