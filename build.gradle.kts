plugins {
    kotlin("multiplatform") version "1.7.0" apply false
    kotlin("plugin.serialization") version "1.7.0" apply false
}

group = "com.brandontalbot"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
    }
}

tasks {
    register<Copy>("bundleTogether") {
        group = "distribution"
        val jsBrowserDistribution = childProjects["web"]!!.tasks.named("browserDistribution")
        childProjects["server"]!!.tasks.named<Copy>("processResources") {
            from(jsBrowserDistribution)
        }
        val serverZip = childProjects["server"]!!.tasks.named("distZip")
        dependsOn()
        from(serverZip)
        from("./docker")
        into(buildDir.path + "/distributions/docker")
    }
}

