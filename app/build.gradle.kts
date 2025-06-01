plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.dagger.hilt.android")
}

android {


    namespace = "com.example.flashlearn_app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.flashlearn_app"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }

    kapt {
        javacOptions {
            option("-J--add-opens", "jdk.compiler/com.sun.tools.javac.code=ALL-UNNAMED")
            option("-J--add-opens", "jdk.compiler/com.sun.tools.javac.comp=ALL-UNNAMED")
            option("-J--add-opens", "jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED")
            option("-J--add-opens", "jdk.compiler/com.sun.tools.javac.main=ALL-UNNAMED")
            option("-J--add-opens", "jdk.compiler/com.sun.tools.javac.model=ALL-UNNAMED")
            option("-J--add-opens", "jdk.compiler/com.sun.tools.javac.parser=ALL-UNNAMED")
            option("-J--add-opens", "jdk.compiler/com.sun.tools.javac.processing=ALL-UNNAMED")
            option("-J--add-opens", "jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED")
            option("-J--add-opens", "jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED")
        }
    }

}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.2")

    implementation("androidx.compose.ui:ui:1.5.4")
    implementation("androidx.compose.material3:material3:1.3.2")
    implementation("androidx.compose.material:material-icons-extended:1.5.4")

    implementation("androidx.navigation:navigation-compose:2.7.7")

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-compiler:2.48.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")


    implementation("androidx.compose.ui:ui-tooling-preview:1.5.4")
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.4")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
