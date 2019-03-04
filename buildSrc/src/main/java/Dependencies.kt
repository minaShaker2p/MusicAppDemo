// 1
object Versions {
    // 2
    const val kotlin = "1.3.11"
    const val supportLibrary = "28.0.0"

    // Build Config
    const val minSDK = 21
    const val compileSDK = 28
    const val targetSDK = 28
}

// 3
object Deps {
    // 4
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"

    // Support Library
    const val appCompat = "com.android.support:appcompat-v7:${Versions.supportLibrary}"
    const val supportAnnotations = "com.android.support:support-annotations:${Versions.supportLibrary}"
    const val constraintLayout = "com.android.support.constraint:constraint-layout:${Versions.supportLibrary}"
    const val supportdesign = "com.android.support:design:${Versions.supportLibrary}"

}