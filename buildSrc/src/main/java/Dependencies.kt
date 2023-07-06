object Dependencies {

    object Kotlin {
        const val version = "1.8.21"
        private const val coroutinesVersion = "1.7.0"
        private const val serializationVersion = "1.4.1"
        private const val collectionsVersion = "0.3.5"

        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
        const val collections = "org.jetbrains.kotlinx:kotlinx-collections-immutable:$collectionsVersion"
    }

    object Compose {
        private const val bomVersion = "2023.05.01"
        const val compilerVersion = "1.4.7"

        const val bom = "androidx.compose:compose-bom:$bomVersion"
        const val ui = "androidx.compose.ui:ui"
        const val runtime = "androidx.compose.runtime:runtime"
        const val tooling = "androidx.compose.ui:ui-tooling"
        const val preview = "androidx.compose.ui:ui-tooling-preview"
        const val material = "androidx.compose.material:material"
        const val materialIcons = "androidx.compose.material:material-icons-extended"
        const val foundation = "androidx.compose.foundation:foundation"
    }

    object Hilt {
        const val version = "2.44.2"
        const val navigationVersion = "1.0.0"

        const val hilt = "com.google.dagger:hilt-android:$version"
        const val kapt = "com.google.dagger:hilt-android-compiler:$version"
        const val navigation = "androidx.hilt:hilt-navigation-compose:$navigationVersion"
    }

    object Retrofit {
        const val version = "2.9.0"
        const val serializationVersion = "0.8.0"

        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val gson = "com.squareup.retrofit2:converter-gson:$version"
    }

    object OkHttp {
        const val version = "4.10.0"

        const val okhttp = "com.squareup.okhttp3:okhttp:$version"
        const val interceptor = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object Lifecycle {
        const val lifecycleVersion = "2.4.1"
        const val composeViewModelVersion = "2.4.0-rc01"
        const val activityVersion = "1.4.0"

        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
        const val lifecycleViewmodel = "androidx.lifecycle:lifecycle-viewmodel-compose:$composeViewModelVersion"
        const val activity = "androidx.activity:activity-compose:$activityVersion"
    }

    object Coil {
        const val version = "2.3.0"

        const val compose = "io.coil-kt:coil-compose:$version"
    }

    object Navigation {
        const val navigationVersion = "2.4.2"

        const val navigation = "androidx.navigation:navigation-compose:$navigationVersion"
    }

    object AndroidX {
        private const val coreVersion = "1.10.0"
        private const val appCompatVersion = "1.6.1"
        private const val activityComposeVersion = "1.7.1"
        private const val lifecycleViewmodelVersion = "2.6.1"
        private const val preferenceVersion = "1.2.0"

        const val core = "androidx.core:core-ktx:$coreVersion"
        const val preference = "androidx.preference:preference-ktx:$preferenceVersion"
        const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"
        const val appCompatResources = "androidx.appcompat:appcompat-resources:$appCompatVersion"
        const val activityCompose = "androidx.activity:activity-compose:$activityComposeVersion"
        const val lifecycleViewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleViewmodelVersion"
    }
}