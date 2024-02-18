
plugins {
    id(Dependencies.Plugins.android_library)
    id(Dependencies.Plugins.kotlin_android)
    id(Dependencies.Plugins.ksp)
}

android {
    namespace = Dependencies.Application.mainId
    compileSdk = Versions.compileSDK

    defaultConfig {
        minSdk = Versions.minsdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = Dependencies.Application.releaseMinify

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
        debug {
            isMinifyEnabled = Dependencies.Application.debugMinify

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Compose.kotlinCompiler
    }
    compileOptions {
        sourceCompatibility = Dependencies.Java.java_compile
        targetCompatibility = Dependencies.Java.java_compile
    }
    kotlinOptions {
        jvmTarget = Dependencies.Java.java_versions
    }
}

dependencies {

    implementation(platform(Dependencies.Compose.composeBoom))
    implementation(Dependencies.Compose.toolingPreview)
    implementation(Dependencies.Dependencies.android_core_ktx)
    implementation(Dependencies.Dependencies.lifecycle_runtime)
    implementation(Dependencies.Dependencies.material)
    implementation(Dependencies.Compose.activityCompose)
    implementation(Dependencies.Compose.composeUi)
    implementation(Dependencies.Compose.composeUiGraphics)
    implementation(Dependencies.Compose.composeUiGraphicsFont)
    implementation(Dependencies.Compose.composeFoundation)
    implementation(Dependencies.Compose.material3)
    implementation(Dependencies.Compose.compose_permissions)
    implementation(Dependencies.Dependencies.window)
    implementation(Dependencies.Compose.fullSetIconsCompose)
    implementation(Dependencies.Compose.lottie)

    //koin
    implementation(Dependencies.Dependencies.koin_android)
    implementation(Dependencies.Dependencies.koin_compose)

    //retrofit
    implementation(Dependencies.Dependencies.retrofit)
    implementation(Dependencies.Dependencies.okhttp)
    implementation(Dependencies.Dependencies.converter_gson)
    implementation(Dependencies.Dependencies.okhttp_login_interceptor)
    implementation(Dependencies.Compose.coilCompose)

    implementation(Dependencies.Dependencies.orbit_compose)
    implementation(Dependencies.Dependencies.orbit_viewModel)

    implementation(Dependencies.Compose.destinations)
    ksp(Dependencies.Compose.kspDestinations)
}