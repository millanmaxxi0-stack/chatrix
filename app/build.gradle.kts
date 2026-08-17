plugins {
    id("com.android.application")
}

android {
    namespace = "com.chatrix.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.chatrix.app"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"
    }

    signingConfigs {
        create("release") {
            // These come from environment variables set by the GitHub Actions
            // workflow (from repository secrets) — never hardcoded here, and
            // never committed. Locally in Android Studio, set the same three
            // env vars yourself, or just build a debug APK for testing.
            val ksPath = System.getenv("KEYSTORE_PATH")
            if (ksPath != null) {
                storeFile = file(ksPath)
                storePassword = System.getenv("KEYSTORE_PASSWORD")
                keyAlias = "chatrix"
                keyPassword = System.getenv("KEY_PASSWORD")
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    // Official Google library for Trusted Web Activities — this is what makes
    // the wrapped app use real Chrome underneath (real Service Worker, real
    // Push API, real notifications) instead of a stripped-down WebView.
    implementation("com.google.androidbrowserhelper:androidbrowserhelper:2.6.0")
}
