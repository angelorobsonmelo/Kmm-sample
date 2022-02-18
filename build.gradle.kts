buildscript {
    repositories {
        gradlePluginPortal()
        google()
//        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${properties["version.kotlin"]}")
        classpath("com.android.tools.build:gradle:7.1.1")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${properties["version.kotlin"]}")
        classpath ("com.squareup.sqldelight:gradle-plugin:${properties["version.sqlDelight"]}")
    }
}

allprojects {
    repositories {
        google()
//        jcenter()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}