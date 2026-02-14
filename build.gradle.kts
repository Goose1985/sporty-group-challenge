plugins {
    java
    id("org.springframework.boot") version "3.3.5"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "com.sporty.group"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
}

dependencies {

    // --- Core ---
    implementation("org.springframework.boot:spring-boot-starter")

    // --- REST API ---
    implementation("org.springframework.boot:spring-boot-starter-web")

    // --- Kafka ---
    implementation("org.springframework.kafka:spring-kafka")

    // --- Testing ---
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.kafka:spring-kafka-test")
}

tasks.test {
    useJUnitPlatform()
}
