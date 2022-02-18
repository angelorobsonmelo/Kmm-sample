plugins {
    kotlin("multiplatform")
    id("com.squareup.sqldelight")
    kotlin("native.cocoapods")
    id("com.android.library")
    kotlin("plugin.serialization")
}

version = "1.0"

kotlin {
    android()
    iosX64()
    iosArm64()
    //iosSimulatorArm64() sure all ios dependencies support this target

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val ktorVersion = "1.6.3"
        val napierVersion = "1.4.1"
        val kodeinVersion = "7.8.0"
        val mokoMvvmVersion = "0.11.0"
        val kotlinxSerializationCoreVersion = "1.2.2"

        val commonMain by getting {
            dependencies {
                //Logger
                implementation("io.github.aakira:napier:$napierVersion")
                implementation("org.kodein.di:kodein-di:$kodeinVersion")

                // Ktor
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")

                // Serialization
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlinxSerializationCoreVersion")

                // MOKO - MVVM
                implementation("dev.icerock.moko:mvvm:$mokoMvvmVersion")

                // SqlDelight
                implementation("com.squareup.sqldelight:runtime:${properties["version.sqlDelight"]}")

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                // Ktor
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")

                // SqlDelight
                implementation("com.squareup.sqldelight:android-driver:${properties["version.sqlDelight"]}")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }

        val iosX64Main by getting

        val iosArm64Main by getting
        //val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            dependencies {
                // SqlDelight
                implementation("com.squareup.sqldelight:native-driver:${properties["version.sqlDelight"]}")

                // Ktor
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
            }
            //iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        //val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            //iosSimulatorArm64Test.dependsOn(this)
        }
    }
}


sqldelight {
    database("PostDatabase") {
        packageName = "com.angelorobson.opsmoonkmm.db"
        sourceFolders = listOf("sqldelight")
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }
}