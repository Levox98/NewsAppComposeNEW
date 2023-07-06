import org.gradle.api.JavaVersion

object Config {
    const val packageName = "com.levox.newsapp"

    const val compileSdk = 33
    const val minSdk = 26
    const val targetSdk = 33

    const val versionCode = 1
    const val versionName = "1.0.0"

    const val jvmTarget = "1.8"
    val sourceCompatibility = JavaVersion.VERSION_1_8
    val targetCompatibility = JavaVersion.VERSION_1_8
}