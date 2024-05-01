plugins {
	java
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
	jacoco
}

group = "com.adproa3.microservice"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	runtimeOnly("org.postgresql:postgresql")
	compileOnly("org.springframework.boot:spring-boot-devtools")
	implementation("jakarta.validation:jakarta.validation-api:3.1.0-M2")
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")

}

//tasks.test {
//	useJUnitPlatform()
//	finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
//}
//tasks.jacocoTestReport {
//	classDirectories.setFrom(files(classDirectories.files.map {
//		fileTree(it) { exclude("**/*Application**") }
//	}))
//	dependsOn(tasks.test) // tests are required to run before generating the report
//	reports {
//		xml.required.set(false)
//		csv.required.set(false)
//		html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
//	}
//}




