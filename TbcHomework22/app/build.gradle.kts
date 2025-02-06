plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.safeargs)
    alias(libs.plugins.kotlin.serialization)
    id("com.google.protobuf") version "0.9.4"
    id("androidx.room")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.tbchomework22"
    compileSdk = 35

    room {
        schemaDirectory("$projectDir/schemas")
    }

    defaultConfig {
        applicationId = "com.example.tbchomework22"
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
    buildFeatures{
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

    val room_version = "2.6.1"

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
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.0")
    ksp("androidx.room:room-compiler:2.5.0")
    implementation(libs.navigation.ui)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.filament.android)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation("androidx.room:room-ktx:$room_version")
    implementation("androidx.room:room-runtime:$room_version")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}