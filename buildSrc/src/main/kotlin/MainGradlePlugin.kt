import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class MainGradlePlugin: Plugin<Project> {

    override fun apply(project: Project) {
        setAndroidConfig(project)
    }

    private fun setAndroidConfig(project: Project) {
        project.android().apply {
            compileSdk = Application.compileSDK

            defaultConfig {
                minSdk = Application.minsdk
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

            buildTypes {
                release {
                    isMinifyEnabled = Application.releaseMinify

                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                    signingConfig = signingConfigs.getByName("debug")
                }
                debug {
                    isMinifyEnabled = Application.debugMinify

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
                sourceCompatibility = Application.Java.java_compile
                targetCompatibility = Application.Java.java_compile
            }
        }
    }

    private fun Project.android() : LibraryExtension {
        return extensions.getByType(LibraryExtension::class.java)
    }
}


