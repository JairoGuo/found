/**
 * 在plugins中直接使用libs可以运行，但会出现额外的显示错误，已压制
 * 但在allprojects中使用libs无法运行，使用变量却可以
 */
var deps = libs

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    java
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.dependency.management)
    id("java-library")

}


group = "com.jairoguo"
version = "1.0-SNAPSHOT"

allprojects {
    apply {
        plugin("java")
    }

    java.sourceCompatibility = org.gradle.api.JavaVersion.VERSION_17
    java.targetCompatibility = org.gradle.api.JavaVersion.VERSION_17

    group = "com.jairoguo"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenLocal()
        maven("https://repo.huaweicloud.com/repository/maven/")
        mavenCentral()
    }
    dependencies {
    }
}

subprojects {
    apply {
        plugin("java")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
    }
    dependencies {
        implementation(platform(deps.spring.cloud))
        implementation(platform(deps.spring.cloud.alibaba))
        platform(deps.spring.boot)
        testImplementation(deps.jupiter.api)
        testRuntimeOnly(deps.jupiter.engine)
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")
    }

    tasks.getByName<Test>("test") {
        useJUnitPlatform()
    }

}


project(":infra") {
    apply {

    }
    dependencies {
        implementation("org.springframework:spring-web")
        implementation("org.springframework:spring-context")
        implementation("org.springframework:spring-webmvc")
        implementation("org.springframework:spring-webmvc")
        implementation("org.springframework.boot:spring-boot-autoconfigure")
        implementation("org.apache.tomcat.embed:tomcat-embed-core")
        implementation("io.github.openfeign:feign-core")
        implementation("com.fasterxml.jackson.core:jackson-databind")
        implementation(deps.bundles.log4j)

    }

}

project(":apps") {
    apply {
        plugin("org.springframework.boot")
        plugin("java-library")

    }

    subprojects {
        dependencies {
            implementation(project(":infra"))
            implementation("org.springframework.boot:spring-boot-starter-web")
            implementation("org.springframework.boot:spring-boot-starter-test")
        }
    }

}

