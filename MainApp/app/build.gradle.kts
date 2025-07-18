plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("plugin.serialization") version "2.1.10"
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.deepshield"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.deepshield"
        minSdk = 27
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.example.deepshield.di.HiltAndroidTestRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    //SplashScreen
    //implementation("androidx.core:core-splashscreen:1.0.0")
    implementation("io.ktor:ktor-client-core:2.3.5") // Core Ktor client
    implementation("io.ktor:ktor-client-cio:2.3.5")  // For making network requests
    implementation("io.ktor:ktor-client-content-negotiation:2.3.5") // For automatic JSON serialization
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.5") // Kotlinx serialization support
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0") // JSON parser
    //navigation JETPACK COMPOSE
    val nav_version = "2.8.6"
    implementation("androidx.navigation:navigation-compose:$nav_version")
    //Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    //Most Error making Library
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    //Coil Image Loading
    implementation("io.coil-kt.coil3:coil-compose:3.1.0")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.1.0")
    implementation("com.airbnb.android:lottie-compose:6.4.0")
    implementation ("io.github.shashank02051997:FancyToast:2.0.2")
    //mediaplayer
    implementation("androidx.media3:media3-exoplayer:1.3.1")
    implementation("androidx.media3:media3-ui:1.3.1")

    //extended icons
    implementation ("androidx.compose.material:material-icons-core:1.6.7")
    implementation ("androidx.compose.material:material-icons-extended:1.6.7")



    //Mock Test ktor
    testImplementation("io.ktor:ktor-client-mock:2.3.5")

    androidTestImplementation("io.ktor:ktor-client-mock:2.3.5")


    //hilt testing
    // For Robolectric tests.
    testImplementation("com.google.dagger:hilt-android-testing:2.51.1")
    // ...with Kotlin.
    kaptTest("com.google.dagger:hilt-android-compiler:2.51.1")
    // ...with Java.
    testAnnotationProcessor("com.google.dagger:hilt-android-compiler:2.51.1")


    // For instrumented tests.
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.51.1")
    // ...with Kotlin.
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.51.1")
    // ...with Java.
    androidTestAnnotationProcessor("com.google.dagger:hilt-android-compiler:2.51.1")





}