Installation
============

Use SOAP connector
------------------

```
soap.client.props
DummyClientTrustFile.jks
DummyClientKeyFile.jks
```


Development
===========

Dependencies
------------
You need to install the dependencies into your local mvn repository via mvn install!
```
mvn install:install-file -Dfile={PATH FILE}com.ibm.ws.admin.client_8.5.0.jar -DgroupId=com.ibm.ws -DartifactId=admin.client -Dversion=8.5.0 -Dpackaging=jar

mvn install:install-file -Dfile={PATH FILE}com.ibm.ws.orb_8.5.0.jar -DgroupId=com.ibm.ws -DartifactId=admin.client -Dversion=8.5.0 -Dpackaging=jar
```

