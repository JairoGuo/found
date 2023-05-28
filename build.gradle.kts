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
        plugin("java-library")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
    }
    dependencies {
        implementation(platform(deps.spring.cloud))
        implementation(platform(deps.spring.cloud.alibaba))
//        implementation(platform(deps.spring.boot))
    }

    tasks.getByName<Test>("test") {
        useJUnitPlatform()
    }

}


project(":infra") {
    apply {

    }
    dependencies {

//        api(deps.bundles.log4j)
        api(deps.slf4j)
        api(deps.bundles.junit)


    }

}

project(":apps") {


    subprojects {
        apply {
            plugin("org.springframework.boot")
            plugin("java-library")
        }

        dependencies {
            implementation(project(":infra"))
            implementation(project(":core:data-core"))
            implementation(project(":core:web-core"))

            implementation("org.springframework.boot:spring-boot-starter-web")
            testImplementation("org.springframework.boot:spring-boot-starter-test")

            compileOnly("org.projectlombok:lombok")
            annotationProcessor("org.projectlombok:lombok")

            testImplementation(deps.jupiter.api)
            testRuntimeOnly(deps.jupiter.engine)
        }
    }

    project(":apps:app1") {

        dependencies {
            api("org.springframework.boot:spring-boot-starter-data-mongodb")

        }
    }

}

project(":core") {

    subprojects {
        apply {
        }

        dependencies {
            implementation(project(":infra"))

        }
    }

    project(":core:data-core") {
        dependencies {
            api("org.springframework.data:spring-data-mongodb")
            api("org.springframework:spring-context")


//            api(deps.mybatis.plus)


        }
    }

    project(":core:web-core") {
        dependencies {
            implementation("org.springframework:spring-webmvc")
            implementation("org.springframework:spring-web")

            implementation("org.apache.tomcat.embed:tomcat-embed-core")
            implementation("io.github.openfeign:feign-core")
            implementation("com.fasterxml.jackson.core:jackson-databind")


//            api(deps.mybatis.plus)


        }
    }
}

