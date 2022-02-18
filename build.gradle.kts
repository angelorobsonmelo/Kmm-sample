buildscript {
    repositories {
        gradlePluginPortal()
        google()
//        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("com.android.tools.build:gradle:7.1.0")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${properties["version.kotlin"]}")
//        classpath("com.squareup.sqldelight:gradle-plugin:1.5.1")
        classpath ("com.squareup.sqldelight:gradle-plugin:1.5.3")


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