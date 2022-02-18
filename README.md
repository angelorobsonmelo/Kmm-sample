# Kotlin-Multiplatform MVVM (Android & iOS)

Example of application using Kotlin Multiplatform and MVVM pattern for both platforms (Android & iOS). To achieve it the libraries used are:

- [moko-mvvm](https://github.com/icerockdev/moko-mvvm): This is a Kotlin Multiplatform library that provides architecture components of Model-View-ViewModel for UI applications. Components are lifecycle-aware on Android.
- [KTOR](https://github.com/ktorio/ktor): to make HTTP requests
- [Serialization](https://github.com/Kotlin/kotlinx.serialization): to De/Serializing JSON 
- [Kodein-DI](https://github.com/Kodein-Framework/Kodein-DI): Dependency injector
- [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines): Library support for Kotlin coroutines with multiplatform support
- [SQLDelight](https://github.com/cashapp/sqldelight): SQLDelight generates typesafe kotlin APIs from your SQL statements.

## Installation

Run `gradlew build` from the root of the project. After a successful build, you should be able to install the app on both Android and iOS.

> If you are unable to run the project in Xcode, you may need to add `-lsqlite3` to `Other Linker Flags` as mentioned [here](https://github.com/cashapp/sqldelight/issues/1442#issuecomment-523435492)
