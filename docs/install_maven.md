# Installing Apache Maven

The installation of Apache Maven is a simple process of extracting the archive and adding the bin folder with the mvn command to the PATH.

Detailed steps are:

* Ensure JAVA_HOME environment variable is set and points to your JDK installation

* Extract distribution archive in any directory

```
1. unzip apache-maven-3.3.9-bin.zip
```

or

```
1. tar xzvf apache-maven-3.3.9-bin.tar.gz
```

Alternatively use your preferred archive extraction tool.

* Add the bin directory of the created directory apache-maven-3.3.9 to the PATH environment variable

* Confirm with mvn -v in a new shell. The result should look similar to

```
1. Apache Maven 3.3.3 (7994120775791599e205a5524ec3e0dfe41d4a06; 2015-04-22T04:57:37-07:00)
2. Maven home: /opt/apache-maven-3.3.3
3. Java version: 1.8.0_45, vendor: Oracle Corporation
4. Java home: /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre
5. Default locale: en_US, platform encoding: UTF-8
6. OS name: "mac os x", version: "10.8.5", arch: "x86_64", family: "mac"
```

## Windows Tips

* Check environment variable value e.g.

```
1. echo %JAVA_HOME%
2. C:\Program Files\Java\jdk1.7.0_51
```

* Adding to PATH: Add the unpacked distribution’s bin directory to your user PATH environment variable by opening up the system properties (WinKey + Pause), selecting the “Advanced” tab, and the “Environment Variables” button, then adding or selecting the PATH variable in the user variables with the value C:\Program Files\apache-maven-3.3.9\bin. The same dialog can be used to set JAVA_HOME to the location of your JDK, e.g. C:\Program Files\Java\jdk1.7.0_51

* Open a new command prompt (Winkey + R then type cmd) and run mvn -v to verify the installation.

## Unix-based Operating System (Linux, Solaris and Mac OS X) Tips

* Check environment variable value

```
1. echo $JAVA_HOME
2. /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home
```

* Adding to PATH

```
1. export PATH=/opt/apache-maven-3.3.9/bin:$PATH
```
