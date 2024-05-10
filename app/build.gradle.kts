import com.android.build.api.dsl.Packaging

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.githubchallenge"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.githubchallenge"
        minSdk = 30
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    buildFeatures {
        dataBinding = true
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
        packagingOptions {
            exclude("META-INF/LICENSE.md")
            exclude("META-INF/LICENSE-notice.md")
            jniLibs {
                useLegacyPackaging = true
            }
        }
    }



}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.test.espresso:espresso-contrib:3.5.1")
    testImplementation("junit:junit:4.13.2")
    testImplementation("junit:junit:4.12")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("junit:junit:4.12")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    //retrofit
    val retrofitVersion = "2.11.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")

    //gsonConverter
    val gsonConverterVersion = "2.11.0"
    implementation("com.squareup.retrofit2:converter-gson:$gsonConverterVersion")

    //gson
    val gsonVersion = "2.10.1"
    implementation("com.google.code.gson:gson:$gsonVersion")

    //glide
    val glideVersion = "4.16.0"
    implementation("com.github.bumptech.glide:glide:$glideVersion")

    //Coroutines
    val coroutinesVersion = "1.7.3"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

    //Hilt
    val hilVersion = "2.44"
    implementation("com.google.dagger:hilt-android:$hilVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hilVersion")

    //LiveData
    val lifecycleVersion = "2.7.0"
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")

    //mockito-kotlin
    val mockitoKotlinVersion = "5.3.1"
    val mockitoCoreVersion = "5.11.0"
    testImplementation("org.mockito.kotlin:mockito-kotlin:$mockitoKotlinVersion")
    androidTestImplementation("org.mockito:mockito-core:$mockitoCoreVersion")

    val mockkVersion = "1.13.10"
    androidTestImplementation("io.mockk:mockk-android:$mockkVersion")


    val mockWebserverVersion = "4.12.0"
    testImplementation("com.squareup.okhttp3:mockwebserver:$mockWebserverVersion")

    val testRulesVersion = "1.5.0"
    androidTestImplementation("androidx.test:rules:$testRulesVersion")

}