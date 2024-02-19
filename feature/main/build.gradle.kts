plugins {
    `android-library`
    `kotlin-android`
    id(Plugins.ksp)
}

apply<MainGradlePlugin>()

android {
    namespace = Application.mainId
}

dependencies {

    implementation(platform(Dependencies.Compose.composeBoom))
    implementation(Dependencies.Compose.toolingPreview)
    implementation(Dependencies.android_core_ktx)
    implementation(Dependencies.lifecycle_runtime)
    implementation(Dependencies.material)
    implementation(Dependencies.Compose.activityCompose)
    implementation(Dependencies.Compose.composeUi)
    implementation(Dependencies.Compose.composeUiGraphics)
    implementation(Dependencies.Compose.composeUiGraphicsFont)
    implementation(Dependencies.Compose.composeFoundation)
    implementation(Dependencies.Compose.material3)
    implementation(Dependencies.Compose.compose_permissions)
    implementation(Dependencies.window)
    implementation(Dependencies.Compose.fullSetIconsCompose)

    //koin
    implementation(Dependencies.koin_android)
    implementation(Dependencies.koin_compose)

    //retrofit
    implementation(Dependencies.retrofit)
    implementation(Dependencies.okhttp)
    implementation(Dependencies.converter_gson)
    implementation(Dependencies.okhttp_login_interceptor)
    implementation(Dependencies.Compose.coilCompose)

    implementation(Dependencies.orbit_compose)
    implementation(Dependencies.orbit_viewModel)

    implementation(Dependencies.Compose.destinations)
    ksp(Dependencies.Compose.kspDestinations)
}