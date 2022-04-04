import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.asciidoctor.convert") version "1.5.8"
    id("io.gitlab.arturbosch.detekt") version "1.17.0-RC2"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
    kotlin("plugin.jpa") version "1.6.10"
    id("jacoco")
    id ("org.sonarqube") version "3.3"
}

group = "com.itau"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

extra["snippetsDir"] = file("build/generated-snippets")
extra["springCloudVersion"] = "2021.0.1"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.cloud:spring-cloud-starter-sleuth")
    implementation("org.springdoc:springdoc-openapi-ui:1.6.6")
    implementation("br.com.caelum.stella:caelum-stella-core:2.1.2")
    implementation("io.gitlab.arturbosch.detekt:detekt-cli:1.16.0")
    implementation("org.flywaydb:flyway-core")
    runtimeOnly("mysql:mysql-connector-java")
    testImplementation("org.testcontainers:mysql:1.15.2")
    testImplementation("com.github.database-rider:rider-junit5:1.32.3")
    testImplementation("io.rest-assured:rest-assured")
    testImplementation("io.github.serpro69:kotlin-faker:1.10.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.withType<JacocoCoverageVerification> {
    violationRules {
        rule {
            limit {
                minimum = "0.9".toBigDecimal()
            }
        }
    }
}

tasks.withType<JacocoReport> {
    reports {
        xml.required.set(true)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}

sonarqube {
    properties {
        property("sonar.login", "5a84b48f32ed25951ce19f1f93f7f1a9c34bac9d")
        property("sonar.host.url", "http://localhost:9000")
        property("sonar.projectKey", "itau")
        property("sonar.coverage.jacoco.xmlReportPaths", "${project.buildDir}/reports/jacoco/test/jacocoTestReport.xml")
        property("sonar.sources", "src/main")
        property("sonar.tests", "src/test")
    }
}
