import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension

plugins {
    kotlin("js")
}

kotlin {
    js(IR) {
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
        binaries.executable()
    }
}

rootProject.extensions.configure<NodeJsRootExtension> {
    versions.webpackCli.version = "4.10.0"
}

dependencies {
    implementation(project(":common"))

    implementation("org.jetbrains.kotlin-wrappers:kotlin-react:18.1.0-pre.345")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:18.1.0-pre.345")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-router-dom:6.3.0-pre.345")
    implementation(npm("react", "18.1.0"))
    implementation(npm("react-dom", "18.1.0"))
    implementation(npm("react-router-dom", "6.3.0"))

    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-css:18.0.0-pre.331-kotlin-1.6.20")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")
}
