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

//kotlin {
//    jvm {
//        compilations.all {
//            kotlinOptions.jvmTarget = "1.8"
//        }
//        withJava()
//        testRuns["test"].executionTask.configure {
//            useJUnitPlatform()
//        }
//    }
//    js(IR) {
//        binaries.executable()
//        browser {
//            commonWebpackConfig {
//                cssSupport.enabled = true
//            }
//        }
//    }
//    sourceSets {
//        val ktorVersion = "2.0.3"
//        val commonMain by getting {
//            dependencies {
//                implementation("io.ktor:ktor-client-core:$ktorVersion")
//                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
//                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
//                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
//                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.3")
//            }
//        }
//        val commonTest by getting {
//            dependencies {
//                implementation(kotlin("test"))
//            }
//        }
//        val jvmMain by getting {
//            dependencies {
//                implementation("io.ktor:ktor-server-core:$ktorVersion")
//                implementation("io.ktor:ktor-server-netty:$ktorVersion")
//                implementation("io.ktor:ktor-server-html-builder:$ktorVersion")
//                implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
//                implementation("io.ktor:ktor-utils-jvm:$ktorVersion")
//
//                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
//                implementation("io.ktor:ktor-client-logging:$ktorVersion")
//
//                implementation("ch.qos.logback:logback-classic:1.3.0-alpha16")
//                implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.2")
//            }
//        }
//        val jvmTest by getting
//        val jsMain by getting {
//            dependencies {
//                implementation("org.jetbrains.kotlin-wrappers:kotlin-react:18.1.0-pre.345")
//                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:18.1.0-pre.345")
//                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-css:18.0.0-pre.331-kotlin-1.6.20")
//                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
//            }
//        }
//        val jsTest by getting
//    }
//}
