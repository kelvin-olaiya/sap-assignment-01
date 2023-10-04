plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation("com.google.guava:guava:32.1.1-jre")

    // Vert.x
    implementation("io.vertx:vertx-core:4.4.5")
    implementation("io.vertx:vertx-web:4.4.5")

    // Socket.IO
    implementation("com.corundumstudio.socketio:netty-socketio:2.0.3")
}

application {
    mainClass.set("assignment01.Application")
}
