plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.safeargs)
    alias(libs.plugins.kotlin.serialization)
    id("com.google.protobuf") version "0.9.4"
}

android {
    namespace = "com.example.tbchomework21"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.tbchomework21"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }

}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:4.29.0"
    }
    generateProtoTasks {
        all().configureEach {
            plugins {
                create("java") {
                    option("lite")
                }
            }
        }
    }
}


dependencies {

    implementation(libs.androidx.datastore.preferences)
    implementation(libs.kotlinx.serialization.json)
    implementation (libs.retrofit2.retrofit)
    implementation(libs.moshi.kotlin)
    implementation (libs.squareup.converter.moshi)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.navigation.fragment)
    implementation(libs.paging)
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation("androidx.datastore:datastore:1.0.0")
    implementation("com.google.protobuf:protobuf-javalite:3.21.11")
    implementation("com.google.protobuf:protobuf-kotlin-lite:3.21.11")
    implementation(libs.navigation.ui)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.filament.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}




