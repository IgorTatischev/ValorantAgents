buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Plugins.android_build_gradle)
        classpath(Plugins.kotlin_version)
    }
}

plugins {
    id(Plugins.ksp) version ("1.8.10-1.0.9") apply false
}

allprojects {

    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
