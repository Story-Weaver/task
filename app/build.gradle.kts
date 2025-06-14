plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.hilt)
}

android {
    namespace = "by.roman.test_app"
    compileSdk = 35

    defaultConfig {
        applicationId = "by.roman.test_app"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.hilt.android)
    implementation(libs.okhttp)
    implementation(libs.retrofit)
    implementation(libs.convertergson)

    testImplementation(libs.junit)

    annotationProcessor(libs.hilt.compiler)

    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}