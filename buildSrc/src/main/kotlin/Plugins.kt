object Plugins {
    //projects plugins
    const val dagger_plugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    const val android_build_gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlin_version = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val serialization = "org.jetbrains.kotlin.plugin.serialization"

    //app plugins
    const val android_application = "com.android.application"
    const val android_library = "com.android.library"
    const val kotlin_android = "org.jetbrains.kotlin.android"
    const val kapt = "kotlin-kapt"
    const val dagger_hilt = "dagger.hilt.android.plugin"
    const val kotlin_parcelize = "kotlin-parcelize"
    const val ksp = "com.google.devtools.ksp"
}