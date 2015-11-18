Stats4Was
=========
[![Build Status](https://travis-ci.org/cosm0s/stats4was.png)](https://travis-ci.org/cosm0s/stats4was)

requirements
------------
Java 1.7

How To
============

You can use this collector as application whit ./stats4was.sh or also can install daemon whit our installation script

Options
-------

-lb -port 8879 -host localhost
```
2015-11-18 22:23:35,522 [INFO|default|L4j] name:Scheduler_Config_Helper type:WASSchedulerCfgHelper cell:#### node:#### process:#### spec:1.0  mbeanIdentifier:null version:8.5.5.4 platform:#### j2eeType:#### J2EEServer:#### Server:#### mbeanIdentifier:Scheduler_Config_Helper
2015-11-18 22:23:35,523 [INFO|default|L4j] name:CacheableTokenMBean type:CacheableTokenMBean cell:#### node:#### process:#### spec:1.0  mbeanIdentifier:null version:8.5.5.4 platform:#### j2eeType:###~ J2EEServer:null Server:null mbeanIdentifier:CacheableTokenMBean
2015-11-18 22:23:35,523 [INFO|default|L4j] name:SoapConnectorThreadPool type:ThreadPool cell:#### node:#### process:#### spec:1.0  mbeanIdentifier:null version:8.5.5.4 platform:#### j2eeType:null J2EEServer:null Server:null mbeanIdentifier:SoapConnectorThreadPool
```

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

