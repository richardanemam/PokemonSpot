object Libs {

    //UI
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    //Test
    const val junit = "junit:junit:${Versions.junit}"
    const val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    //Gradle Tools
    const val gradleTools = "com.android.tools.build:gradle:${Versions.gradle}"

    //Kotlin
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}