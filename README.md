Stats4Was
=========
[![Build Status](https://travis-ci.org/cosm0s/stats4was.png)](https://travis-ci.org/cosm0s/stats4was)

requirements
------------
Java 1.7

Installation
============

Use SOAP connector
------------------
You need import the next file of our WebSphere installation

```
soap.client.props
ClientTrustFile (DummyClientTrustFile.jks)
ClientKeyFile (DummyClientKeyFile.jks)
```

Development
===========

Dependencies
------------
If you want work whit local dependencies, can install admin.client and orb in local reposiroty:
```
mvn install:install-file -Dfile={PATH FILE}com.ibm.ws.admin.client_8.5.0.jar -DgroupId=com.ibm.ws -DartifactId=admin.client -Dversion=8.5.0 -Dpackaging=jar

mvn install:install-file -Dfile={PATH FILE}com.ibm.ws.orb_8.5.0.jar -DgroupId=com.ibm.ws -DartifactId=admin.client -Dversion=8.5.0 -Dpackaging=jar
```
Also you need comment
```
        <repositories>
           <repository>
               <id>git-cosm0s</id>
               <name>cosm0s's Git based repo</name>
               <url>https://github.com/cosm0s/mvn-repo/raw/master/</url>
           </repository>
        </repositories>
```

