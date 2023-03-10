plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.secrets)
    alias(libs.plugins.realm.kotlin)
   // id("com.google.gms.google-services")
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
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
        allWarningsAsErrors = false
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
        )
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

    implementation(libs.play.services.auth)

    implementation(libs.stevdzachan.onetapcompose)
    implementation(libs.stevdzachan.messagebarcompose)

    implementation(libs.realm.base)
    implementation(libs.realm.sync)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.play.services)

    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.ext.test)
    coreLibraryDesugaring(libs.desugar.jdk.libs)

    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.test.junit4)
    debugImplementation(libs.compose.ui.tooling.test)
    debugImplementation(libs.compose.ui.test.manifest)

}