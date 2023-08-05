plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")

    id("org.jetbrains.compose")
    id("com.squareup.sqldelight")
    kotlin("plugin.serialization")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                // Compose
                implementation(compose.animation)
                implementation(compose.animationGraphics)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                // SQLDelight
                implementation(Deps.Sqldelight.runtime)
                implementation(Deps.Sqldelight.coroutinesExtensions)
                implementation(Deps.Kotlinx.DateTime.dateTime)

                // KTOR
                with(Deps.Ktor) {
                    api(core)
                    api(logging)
                    api(negotiation)
                    api(serialization)
                }

                with(Deps.UI) {
                    api(composeView)
                }
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                //put your android dependencies here

                // Compose
                implementation(Deps.Android.appcompat)
                implementation(Deps.Android.activityCompose)

                // SQLDelight
                implementation(Deps.Sqldelight.androidDriver)

                // KTOR
                implementation(Deps.Ktor.ktorAndroid)

                // koin
                implementation(Deps.Koin.android)
            }
        }
        val iosMain by getting {
            dependencies {
                //put your ios dependencies here

                // SQLDelight
                implementation(Deps.Sqldelight.iosDriver)

                // KTOR
                implementation(Deps.Ktor.ktorIOS)
            }
        }
    }
}

android {
    namespace = "net.yuuzu.expanseapp"
    compileSdk = Configurations.compileSdk
    defaultConfig {
        minSdk = Configurations.minSdk
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

sqldelight {
    database("ExpenseDatabase") {
        packageName = "net.yuuzu.expanseapp.data"
        sourceFolders = listOf("sqldelight")
    }
}

dependencies {
    // put your platform dependencies here
    // add on
    implementation(Deps.Android.androidCore)
    commonMainApi(Deps.Moko.mokoCore)
    commonMainApi(Deps.Moko.mokoCompose)
    commonMainApi(Deps.Moko.mokoFlow)
    commonMainApi(Deps.Moko.mokoFlowCompose)
}