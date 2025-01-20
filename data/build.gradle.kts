plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.devtools.ksp")
    id("androidx.room")
}

android {
    namespace = "ru.vafeen.universityschedule.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    room {
        schemaDirectory("$projectDir/schemas")
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":backendDTO"))
    implementation(libs.material)
    testImplementation(libs.junit.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // room
    api(libs.androidx.room.runtime)
    api(libs.androidx.room.common)
    api(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    //Direct-Refresher
    implementation(libs.direct.refresher)
    //ktor
    // Ktor client core
    implementation ("io.ktor:ktor-client-core:2.3.4")
    // Ktor client for Android
    implementation ("io.ktor:ktor-client-okhttp:2.3.4")
    // Json serialization (если нужно)
    implementation ("io.ktor:ktor-client-content-negotiation:2.3.4")
    implementation ("io.ktor:ktor-serialization-kotlinx-json:2.3.4")
}