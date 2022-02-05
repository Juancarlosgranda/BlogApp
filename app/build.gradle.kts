plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = ConfigApp.compileSdk

    defaultConfig {

        applicationId = "com.jc.app.blog"
        minSdk = ConfigApp.minSdk
        targetSdk = ConfigApp.targetSdk
        versionCode = ConfigApp.versionCode
        versionName = ConfigApp.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "BaseURL", "\"https://jsonplaceholder.typicode.com/\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
        dataBinding = true
        viewBinding = true
    }
}

dependencies {

    implementation(Dependencies.core)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.constraintLayout)

    implementation (Dependencies.activity)
    implementation (Dependencies.fragment)
    implementation (Dependencies.runtime)

    implementation (Dependencies.retrofit2)
    implementation (Dependencies.interceptor)
    implementation (Dependencies.gson)
    implementation (Dependencies.retrofit2Converter)

    implementation (Dependencies.coroutines)
    implementation (Dependencies.coroutinesAndroid)

    implementation("androidx.navigation:navigation-ui-ktx:2.4.0")

    api ("com.google.dagger:dagger:2.40")
    kapt ("com.google.dagger:dagger-compiler:2.40")
    implementation ("com.google.dagger:dagger-android:2.40")
    implementation ("com.google.dagger:dagger-android-support:2.40")
    annotationProcessor ("com.google.dagger:dagger-compiler:2.40")
    annotationProcessor ("com.google.dagger:dagger-android-processor:2.40")
    kapt ("com.google.dagger:dagger-android-processor:2.40")

    implementation ("androidx.room:room-runtime:2.4.1")
    kapt ("androidx.room:room-compiler:2.4.1")

    // Kotlin Extensions and Coroutines support for Room
    implementation ("androidx.room:room-ktx:2.4.1")

    testImplementation(DependenciesTest.junit)
    testImplementation(DependenciesTest.mockk)
    testImplementation(DependenciesTest.coroutines)

    androidTestImplementation(DependenciesTest.junitAndroid)
    androidTestImplementation(DependenciesTest.espresso)
}
