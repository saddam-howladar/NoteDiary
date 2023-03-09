plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.kotlin.android).apply(false)
    alias(libs.plugins.google.secrets).apply(false)
    alias(libs.plugins.realm.kotlin).apply(false)
   // id("com.google.gms.google-services").version("4.3.15").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
