plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.secrets)
}

android {
    namespace = "com.shcoding.notediary"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.shcoding.notediary"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}


dependencies {

    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.composeStdLib)
    implementation(libs.bundles.composeIntegrationLib)
    implementation(libs.core.ktx)
    implementation(libs.core.splashscreen)


    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.ext.test)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.test.junit4)
    debugImplementation(libs.compose.ui.tooling.test)
    debugImplementation(libs.compose.ui.test.manifest)

}