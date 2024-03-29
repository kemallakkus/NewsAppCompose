// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.4" apply false
    id("org.jetbrains.kotlin.android") version "1.9.21" apply false
    id("com.google.dagger.hilt.android") version "2.50" apply false
    id("com.google.devtools.ksp") version "1.9.21-1.0.16" apply false
    kotlin("plugin.serialization") version "1.9.21" apply false
    kotlin("jvm") version "1.9.21" apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.4" apply false
    id("com.google.protobuf") version "0.9.4" apply false
    id("com.google.gms.google-services") version "4.4.0" apply false
    id("com.google.firebase.crashlytics") version "2.9.9" apply false
}