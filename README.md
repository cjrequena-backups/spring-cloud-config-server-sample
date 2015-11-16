# spring-cloud-config-server-sample

## Project structure
```yaml
src
    +- main
        +- java
            +-com
                +- sample
	                +-configserver
	                    +- ConfigServerMainApp.java
	                    |
        +- resources
            +- application.yml
            +- bootstrap.yml
```
## Step 1
First you have to create a maven spring boot project and add the maven dependencies.
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.sample</groupId>
	<artifactId>spring-cloud-config-server-sample</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<developers>
		<developer>
			<email>carlosjose.requena@gmail.com</email>
			<name>Carlos Requena</name>
			<url>http://cjrequena.github.io</url>
			<organizationUrl></organizationUrl>
		</developer>
	</developers>
	<scm>
		<connection>scm:git:git//github.com/cjrequena/sample-spring-cloud-config-server.git</connection>
		<developerConnection>scm:git:git@github.com:cjrequena/sample-spring-cloud-config-server.git</developerConnection>
		<url>https://github.com/cjrequena/sample-spring-cloud-config-server.git</url>
	</scm>
	<ciManagement>
		<system></system>
		<url></url>
	</ciManagement>
	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<java.version>1.8</java.version>
		<start-class>com.sample.configserver.ConfigServerApplication</start-class>
		<spring-boot.version>1.2.6.RELEASE</spring-boot.version>
		<spring-cloud.version>1.0.0.RELEASE</spring-cloud.version>
		<lombok.version>1.16.6</lombok.version>
	</properties>
	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-parent</artifactId>
				<version>${spring-boot.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-starter-parent</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-config-server</artifactId>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<version>${lombok.version}</version>
			<scope>provided</scope>
		</dependency>
	</dependencies>
	<build>
		<finalName>${project.artifactId}-${project.version}</finalName>
		<resources>
			<resource>
				<directory>${basedir}/src/main/resources</directory>
				<filtering>true</filtering>
			</resource>
		</resources>
		<plugins>
			<!-- -->
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>2.5.1</version>
				<configuration>
					<source>${java.version}</source>
					<target>${java.version}</target>
					<encoding>${project.build.sourceEncoding}</encoding>
				</configuration>
			</plugin>
			<!-- -->
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-jar-plugin</artifactId>
				<version>2.6</version>
				<configuration>
					<archive>
						<manifest>
							<addDefaultImplementationEntries>true</addDefaultImplementationEntries>
							<addDefaultSpecificationEntries>true</addDefaultSpecificationEntries>
						</manifest>
						<manifestEntries>
							<Specification-Title>${project.artifactId}</Specification-Title>
							<Specification-Version>${project.version}</Specification-Version>
							<Implementation-Title>${project.groupId}.${project.artifactId}</Implementation-Title>
							<Implementation-Build>${buildNumber}</Implementation-Build>
							<Build-Number>${buildNumber}</Build-Number>
						</manifestEntries>
					</archive>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.codehaus.mojo</groupId>
				<artifactId>buildnumber-maven-plugin</artifactId>
				<version>1.3</version>
				<executions>
					<execution>
						<phase>validate</phase>
						<goals>
							<goal>create</goal>
						</goals>
					</execution>
				</executions>
				<configuration>
					<doCheck>false</doCheck>
					<doUpdate>false</doUpdate>
					<timestampFormat>{0,date,yyyy-MM-dd HH:mm:ss}</timestampFormat>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<version>${spring-boot.version}</version>
				<configuration>
					<mainClass>${start-class}</mainClass>
				</configuration>
				<executions>
					<execution>
						<phase>package</phase>
						<goals>
							<goal>repackage</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
		</plugins>
	</build>
</project>

```
## Step 2
Create the ConfigServerApplication.class 

```java
package com.sample.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EnableConfigServer
public class ConfigServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}
}


```
The @SpringBootApplication annotation is equivalent to using @Configuration, @EnableAutoConfiguration and @ComponentScan with their default attributes:

The @RestController annotation is a convenience annotation that does nothing more than adding the @Controller and @ResponseBody annotations

The @EnableConfigServer is a Spring Cloud annotation that takes care of everything necessary to build a configuration server.

## Step 3 Create the application.yml
```yaml
server:
  port: 8888
  
management:
  context-path: /admin
  
logging:
  level:
    com.netflix.discovery: 'OFF'
    org.springframework.cloud: 'DEBUG'
  
spring:
   cloud:
      config:
         server:
            git:
               uri: https://github.com/cjrequena/config-repo
               #username: trolley
               #password: strongpassword
               basedir: target/config
```

## Step 4 Create the bootstrap.yml
```yaml
spring:
  application:
    name: configserver
  config:
    name: configserver
```

