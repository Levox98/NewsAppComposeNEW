plugins {
    id("com.android.library")
    kotlin("android")
}

repositories {
    google()
    mavenCentral()
}

android {
    namespace = "com.levox.base_ui"
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Compose.compilerVersion
    }
}

dependencies {

    implementation(project(":locale"))

    implementation(Dependencies.AndroidX.core)

    //Compose
    implementation(platform(Dependencies.Compose.bom))
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.preview)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.tooling)

    //Lifecycle
    implementation(Dependencies.Lifecycle.activity)

    //Coil
    implementation(Dependencies.Coil.compose)
}