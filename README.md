# GitCodeQuality

This project now uses **Gradle** for builds. The Gradle wrapper scripts are
tracked without the wrapper JAR to keep the repository free of binaries.

## First‑time setup

After cloning the repository you need to generate the wrapper JAR once. Ensure a
local Gradle installation is available and run:

```bash
gradle wrapper --gradle-version 8.4
```

This downloads `gradle/wrapper/gradle-wrapper.jar`. Afterwards you can use the
wrapper for all commands.

## Building

Compile the project with:

```bash
./gradlew build
```

To run the example application:

```bash
./gradlew run
```
