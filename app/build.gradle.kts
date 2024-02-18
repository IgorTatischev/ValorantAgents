plugins {
    id(Plugins.android_application)
    id(Plugins.kotlin_android)
}

android {
    namespace = Application.appId
    compileSdk = Application.compileSDK

    defaultConfig {
        applicationId = Application.appId
        minSdk = Application.minsdk
        targetSdk = Application.targetsdk
        versionCode = Application.version_code
        versionName = Application.version_name

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = Application.releaseMinify
            isShrinkResources = Application.releaseMinify

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
        debug {
            isMinifyEnabled = Application.debugMinify
            isShrinkResources = Application.debugMinify

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Application.Java.java_compile
        targetCompatibility = Application.Java.java_compile
    }
    kotlinOptions {
        jvmTarget = Application.Java.java_versions
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Compose.kotlinCompiler
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(Dependencies.android_core_ktx)
    implementation(Dependencies.lifecycle_runtime)
    implementation(Dependencies.material)
    implementation(Dependencies.Compose.activityCompose)
    implementation(platform(Dependencies.Compose.composeBoom))
    implementation(Dependencies.Compose.composeUi)
    implementation(Dependencies.Compose.composeUiGraphics)
    implementation(Dependencies.Compose.composeUiGraphicsFont)
    implementation(Dependencies.Compose.composeFoundation)
    implementation(Dependencies.Compose.serviceView)
    implementation(Dependencies.Compose.systemUiController)
    implementation(Dependencies.Compose.material3)
    implementation(Dependencies.window)
    implementation(Dependencies.Compose.compose_permissions)
    implementation(Dependencies.Compose.toolingPreview)
    debugImplementation(Dependencies.Compose.debugUiTooling)
    debugImplementation(Dependencies.Compose.debugAndroidCompose)
    debugImplementation(Dependencies.Compose.splash)

    implementation(Dependencies.koin_android)
    implementation(Dependencies.koin_compose)

    implementation(project(Modules.featureMain))
}