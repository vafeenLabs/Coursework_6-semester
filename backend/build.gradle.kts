plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.0.0")
    implementation("io.ktor:ktor-server-core:1.0.0")
    implementation("io.ktor:ktor-server-netty:1.0.0")
    implementation("ch.qos.logback:logback-classic:1.0.0")
}