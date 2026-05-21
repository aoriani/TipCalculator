# TipCalculator

This is a Kotlin Multiplatform (KMP) project designed to introduce Android engineers to KMP and Compose Multiplatform.

## About the App
The TipCalculator is a simple application that allows users to calculate the tip and total bill amount. It supports multiple platforms, including Android, iOS, Desktop (JVM), and Web (Wasm/JS).

## Compose Multiplatform
A key highlight of this project is that the UI is built using **Compose Multiplatform**. As you explore the source code, you'll notice that the Compose code used in `shared/src/commonMain` is almost identical to what you would write for a native Android app. This demonstrates how easily Android skills can be leveraged to build beautiful UIs for other platforms.

## Platform-Specific Implementations (Expect/Actual)
While most of the code is shared, some features require platform-specific logic. This is handled using Kotlin's `expect`/`actual` mechanism.

### The `DollarAmount` Class
To handle currency calculations with precision and proper rounding (Banker's rounding), we use the `DollarAmount` class.
- **`commonMain`**: Defines the `expect class DollarAmount`.
- **`androidMain` & `jvmMain`**: Provide `actual` implementations using `java.math.BigDecimal`.
- **`appleMain`**: Provides an `actual` implementation using `NSDecimalNumber` from Apple's Foundation framework.
- **`webMain`**: Provides an `actual` implementation that leverages a JavaScript library.

### Web Implementation and `external` Keyword
The `webMain` implementation of `DollarAmount` is particularly interesting. Since Kotlin/Wasm and Kotlin/JS can interoperate with JavaScript, we use the `external` keyword to refer to symbols defined in a JavaScript library (`big.js`). This allows us to use mature JS libraries for complex tasks like high-precision arithmetic.

```kotlin
@JsModule("big.js")
private external class Big {
    constructor(value: String)
    fun plus(other: Big): Big
    fun times(other: Big): Big
    // ...
}
```

---

* [/iosApp](./iosApp/iosApp) contains an iOS application. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

* [/shared](./shared/src) is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - [commonMain](./shared/src/commonMain/kotlin) is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    the [iosMain](./shared/src/iosMain/kotlin) folder would be the right place for such calls.
    Similarly, if you want to edit the Desktop (JVM) specific part, the [jvmMain](./shared/src/jvmMain/kotlin)
    folder is the appropriate location.

### Running the apps

Use the run configurations provided by the run widget in your IDE's toolbar. You can also use these commands and options:

- Android app: `./gradlew :androidApp:assembleDebug`
- Desktop app:
  - Hot reload: `./gradlew :desktopApp:hotRun --auto`
  - Standard run: `./gradlew :desktopApp:run`
- Web app:
  - Wasm target (faster, modern browsers): `./gradlew :webApp:wasmJsBrowserDevelopmentRun`
  - JS target (slower, supports older browsers): `./gradlew :webApp:jsBrowserDevelopmentRun`
- iOS app: open the [/iosApp](./iosApp) directory in Xcode and run it from there.

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform),
[Kotlin/Wasm](https://kotl.in/wasm/)…

We would appreciate your feedback on Compose/Web and Kotlin/Wasm in the public Slack channel [#compose-web](https://slack-chats.kotlinlang.org/c/compose-web).
If you face any issues, please report them on [YouTrack](https://youtrack.jetbrains.com/newIssue?project=CMP).
