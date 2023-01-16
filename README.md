# raptor-authorization-service


A platform tool for enabling a security authentication within the Raptor platform in every other backend services as a cross-cutting concern just by configuring the tool
as a dependency.

### How to publish

```
mvn clean deploy -s settings.xml
```

### How to use ?


1. Add the below dependency in the spring boot project or any common spring project in your pom.xml

```
  <dependency>
     <groupId>com.bhn</groupId>
    <artifactId>raptor-authorization-service</artifactId>
    <version>1.0.0-RELEASE</version>
  </dependency>
```

2. Add an extra environment property in your project level as below to disable the security authentication or simply disable the token verification
   for the below mentioned endpoints in a comma separated format. (This property is optional which means it's not mandatory to mention any endpoints/ URL's to disable the auth)

```
raptor.platform.security.unRestricted
```

####### Example

```
raptor.platform.security.unRestricted=/api/all,/api/user
```


```
mvn clean install -s settings.xml
```

##### Prerequisite

Java 8,
Spring boot >=2.7.0

### Todo




