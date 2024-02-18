import org.gradle.api.JavaVersion

object Application {
    const val appId = "com.agents.valorantagents"
    const val mainId = "com.agents.main"

    const val compileSDK = 34
    const val minsdk = 24
    const val targetsdk = 34

    const val version_code = 1
    const val version_name = "1.0.$version_code"

    const val releaseMinify = true
    const val releaseEditable = false

    const val debugMinify = false
    const val debugEditable = true

    object Java {
        const val java_versions = "17"
        const val javaLibraryPlugin = "java-library"
        const val kotlinJmv = "org.jetbrains.kotlin.jvm"
        val java_compile = JavaVersion.VERSION_17
    }
}