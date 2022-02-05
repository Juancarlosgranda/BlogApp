// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        //classpath(BuildPlugins.android)
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath(BuildPlugins.kotlin)
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.4.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}