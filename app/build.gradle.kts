plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    kotlin("kapt") // Kapt'yi Kotlin DSL ile ekle
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
}

android {
    namespace = "com.sametdundar.guaranteeapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sametdundar.guaranteeapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kapt {
        correctErrorTypes = true
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
    implementation(libs.firebase.crashlytics.buildtools)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.coil.compose)
    // Room runtime
    implementation (libs.androidx.room.runtime)
    // Room KTX (Coroutines desteği)
    implementation (libs.androidx.room.ktx)
    implementation (libs.gson)

    // Hilt
    implementation (libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    implementation (libs.androidx.hilt.navigation.fragment)
    kapt(libs.hilt.compiler)

    kapt(libs.androidx.room.compiler.v261)

    implementation (libs.androidx.lifecycle.livedata.ktx)

    // Jetpack Compose
    implementation (libs.ui) // Güncel sürümü kontrol edin
    implementation (libs.androidx.material)
    implementation (libs.ui.tooling.preview)

    // LiveData ile Compose arasında bağ kurmak için
    implementation (libs.androidx.runtime.livedata)

}