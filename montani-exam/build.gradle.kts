buildscript {

	repositories {
		mavenCentral()
	}
	dependencies {
		classpath("org.springframework.boot:spring-boot-gradle-plugin:2.7.5")
	}
}

plugins {
	java
	id("org.springframework.boot") version "2.7.5"
	id ("io.spring.dependency-management") version "1.0.15.RELEASE"
}

apply (plugin = "java")
apply (plugin = "idea")
apply (plugin = "org.springframework.boot")
apply (plugin = "io.spring.dependency-management")

springBoot {
	buildInfo {
		properties {
			group = "com.montani"
			name = "exam"
			version = "1.0.0"
		}
	}
}

java {
	sourceCompatibility = JavaVersion.VERSION_11
	targetCompatibility = JavaVersion.VERSION_11
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.data:spring-data-jpa")
	implementation("org.springdoc:springdoc-openapi-ui:1.1.49")
	implementation("commons-validator:commons-validator:1.7")
	implementation("commons-io:commons-io:2.11.0")


	implementation("org.projectlombok:lombok:1.18.12")
	implementation("org.modelmapper:modelmapper:2.3.5")
	implementation("com.google.guava:guava:31.1-jre")
	implementation("junit:junit:4.13.1")


	annotationProcessor("org.projectlombok:lombok:1.18.12")


	//db
	implementation("org.postgresql:postgresql")
	implementation("com.h2database:h2")


	//test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("com.h2database:h2")
//	testImplementation("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-data-jpa")



}

tasks.getByName<Jar>("bootJar") {
	baseName= "montani-exam"

}
