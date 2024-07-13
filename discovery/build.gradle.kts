plugins {
    id("org.springframework.boot").version("3.3.1")
    id("io.spring.dependency-management").version("1.1.5")
    kotlin("jvm").version("1.9.23")
    kotlin("plugin.spring").version("1.9.23")
    id("io.gitlab.arturbosch.detekt").version("1.23.6")
}

group = "pro.abnjava.examcloud"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "2023.0.2"

dependencies {
    implementation(kotlin("stdlib"))
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.6")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
    implementation("org.glassfish.jaxb:jaxb-runtime:4.0.5")

    implementation("org.springframework.cloud:spring-cloud-starter")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    developmentOnly("org.springframework.boot:spring-boot-actuator-autoconfigure")
//    implementation("org.springframework.cloud:spring-cloud-starter-sleuth")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

detekt {
    toolVersion = "1.23.6"
    config.setFrom(file("config/detekt/detekt.yml"))
    buildUponDefaultConfig = true // Adjust this based on your needs
    allRules = false // Activate all available (even unstable) rules.
    parallel = true // Uses current amount of cpu cores to speed up analysis.
    baseline = file("config/detekt/baseline.xml") // Use if you have a baseline for ignored issues
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
