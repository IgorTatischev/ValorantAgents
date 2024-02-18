@file:Suppress("MemberVisibilityCanBePrivate", "unused")

import org.gradle.api.JavaVersion

object Dependencies {

    object Application {

        const val appId = "com.agents.valorantagents"
        const val mainId = "com.agents.main"
        const val version_code = 1
        const val version_name = "1.0.$version_code"

        const val releaseMinify = true
        const val releaseEditable = false

        const val debugMinify = false
        const val debugEditable = true
    }

        object AnnotationProcessing {
        const val room_compiler = "androidx.room:room-compiler:${Versions.room}"
        const val dagger_hilt_kapt = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    }

    object Java {
        const val java_versions = "17"
        const val javaLibraryPlugin = "java-library"
        const val kotlinJmv = "org.jetbrains.kotlin.jvm"
        val java_compile = JavaVersion.VERSION_17
    }

    object Compose {

        const val kotlinCompiler = "1.4.3"
        private const val accompanist = "0.33.0-alpha"
        private const val coil = "2.4.0"

        private const val composeBoomVersion = "2023.08.00"

        const val composeBoom = "androidx.compose:compose-bom:$composeBoomVersion"

        /**
         * choice one
         */
        const val material3 = "androidx.compose.material3:material3"
        const val material2 = "androidx.compose.material:material"
        const val composeFoundation = "androidx.compose.foundation:foundation"
        const val composeUi = "androidx.compose.ui:ui"
        const val composeUiGraphics = "androidx.compose.ui:ui-graphics"
        const val composeUiGraphicsFont = "androidx.compose.ui:ui-text-google-fonts:1.6.1"

        /**
         * Android Studio Preview support
         */
        const val toolingPreview = "androidx.compose.ui:ui-tooling-preview"
        const val debugUiTooling = "androidx.compose.ui:ui-tooling"

        /**
         * ui tests
         */
        const val androidComposeTest = "androidx.compose.ui:ui-test-junit4"
        const val debugAndroidCompose = "androidx.compose.ui:ui-test-manifest"

        // Optional - Included automatically by material, only add when you need
        // the icons but not the material library (e.g. when using Material3 or a
        // custom design system based on Foundation)
        const val additionalIconsCompose = "androidx.compose.material:material-icons-core"
        // Optional - Add full set of material icons
        const val fullSetIconsCompose = "androidx.compose.material:material-icons-extended"
        // Optional - Add window size utils
        const val windowsSizeUtils = "androidx.compose.material3:material3-window-size-class"

        // Optional - Integration with activities
        const val activityCompose = "androidx.activity:activity-compose:1.7.2"
        // Optional - Integration with ViewModels
        const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1"
        // Optional - Integration with LiveData
        const val liveDataCompose = "androidx.compose.runtime:runtime-livedata"
        // Optional - Integration with RxJava
        const val rxJavaCompose = "androidx.compose.runtime:runtime-rxjava2"

        const val coilCompose = "io.coil-kt:coil-compose:$coil"
        const val coilSvg = "io.coil-kt:coil-svg:$coil"
        const val coilGifs = "io.coil-kt:coil-gif:$coil"

        const val serviceView = "com.google.accompanist:accompanist-webview:$accompanist"
        const val compose_permissions = "com.google.accompanist:accompanist-permissions:${Versions.accompanist}"

        const val navigation = "com.google.accompanist:accompanist-navigation-animation:$accompanist"
        const val permission = "com.google.accompanist:accompanist-permissions:$accompanist"
        const val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:$accompanist"

        const val destinations = "io.github.raamcosta.compose-destinations:core:${Versions.destinations}"
        const val kspDestinations = "io.github.raamcosta.compose-destinations:ksp:${Versions.destinations}"

        const val lottie = "com.airbnb.android:lottie-compose:6.1.0"
    }

    object Dependencies {
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlin_coroutines}"
        const val fragment_ktx = "androidx.fragment:fragment-ktx:${Versions.fragment_ktx}"

        const val lifecycle_viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val lifecycle_runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"

        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
        const val okhttp_login_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

        const val room_runtime = "androidx.room:room-runtime:${Versions.room}"
        const val room_ktx = "androidx.room:room-ktx:${Versions.room}"

        const val hilt_android = "com.google.dagger:hilt-android:${Versions.hilt}"

        const val nav_compose = "androidx.navigation:navigation-compose:${Versions.nav_version}"
        const val hilt_nav_compose = "androidx.hilt:hilt-navigation-compose:${Versions.compose_hilt_nav}"

        const val android_core_ktx = "androidx.core:core-ktx:${Versions.ktx}"
        const val android_appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val material = "com.google.android.material:material:${Versions.material}"

        const val datastore = "androidx.datastore:datastore-preferences:${Versions.datastore}"
        const val dataStoreProto = "androidx.datastore:datastore:${Versions.datastore}"


        const val koin_android = "io.insert-koin:koin-android:${Versions.koin_version}"
        const val koin_compose = "io.insert-koin:koin-androidx-compose:${Versions.koin_version}"

        const val orbit_viewModel = "org.orbit-mvi:orbit-viewmodel:${Versions.orbit_version}"
        const val orbit_compose = "org.orbit-mvi:orbit-compose:${Versions.orbit_version}"

        const val window = "androidx.window:window:${Versions.window}"

        const val kotlinJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1"
        const val kotlinCollectionsImmutable = "org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.5"
    }

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
        const val gms = "com.google.gms.google-services"
    }
}
