rootProject.name = "found"


// version catalog 在7.4版本正式使用
dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            // ===============================================版本=======================================================

            version("spring-cloud", "2022.0.0")
            version("spring-cloud-alibaba", "2022.0.0.0-RC1")
            version("spring-boot", "3.0.1")
            version("jupiter", "5.8.1")
            version("log4j", "2.19.0")

            // ===============================================依赖=======================================================

            library("spring-cloud", "org.springframework.cloud", "spring-cloud-dependencies")
                .versionRef("spring-cloud")
            library(
                "spring-cloud-alibaba",
                "com.alibaba.cloud",
                "spring-cloud-alibaba-dependencies"
            ).versionRef("spring-cloud-alibaba")
            library("spring-boot", "org.springframework.boot", "spring-boot-dependencies").versionRef("spring-boot")
            library("jupiter-api", "org.junit.jupiter", "junit-jupiter-api").versionRef("jupiter")
            library("jupiter-engine", "org.junit.jupiter", "junit-jupiter-engine").versionRef("jupiter")
            library("log4j-core", "org.apache.logging.log4j", "log4j-core").versionRef("log4j")
            library("log4j-jul", "org.apache.logging.log4j", "log4j-jul").versionRef("log4j")
            library("log4j-slf4j2-impl", "org.apache.logging.log4j", "log4j-slf4j2-impl").versionRef("log4j")

            // ===============================================bundle====================================================
            bundle("log4j", listOf("log4j-core", "log4j-jul", "log4j-slf4j2-impl"))
            // ===============================================插件=======================================================
            plugin("spring-boot", "org.springframework.boot").versionRef("spring-boot")
            plugin("dependency-management", "io.spring.dependency-management").version("1.1.0")
        }
    }

}
include("infra")
include("apps")
include("shared")
include("core")
include("apps:app1")
findProject(":apps:app1")?.name = "app1"
