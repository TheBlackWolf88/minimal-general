# Minimal General

**Minimal General** is a lightweight file manager inspired by Total Commander, developed using Kotlin and Gradle. It aims to provide a minimalist and efficient interface for file navigation and management.

## Features

- **Dual-pane Interface**: Navigate and manage files in two directories simultaneously.
- **Keyboard Shortcuts**: Efficient file operations using customizable keyboard shortcuts.
- **Cross-platform Support**: Runs on any platform with Java support.
- **Lightweight**: Minimal dependencies for fast performance.

## Getting Started

### Prerequisites

- [Java Development Kit (JDK)](https://adoptium.net/) (version 11 or higher)
- [Gradle](https://gradle.org/) (if not using the provided wrapper)

### Installation

1. **Clone the repository**:

   ```bash
   git clone https://github.com/TheBlackWolf88/minimal-general.git
   cd minimal-general
   ```

2. **Build the project**:

   Using the Gradle wrapper:

   ```bash
   ./gradlew build
   ```

   Or using your local Gradle installation:

   ```bash
   gradle build
   ```

3. **Run the application**:

   ```bash
   ./gradlew run
   ```

## Project Structure

- `src/main/kotlin/`: Contains the main application source code.
- `build.gradle.kts`: Kotlin-based Gradle build script.
- `settings.gradle.kts`: Gradle settings file.
- `gradle/`: Contains the Gradle wrapper files.

## Technologies Used

- [Kotlin](https://kotlinlang.org/): Modern programming language for JVM.
- [Gradle](https://gradle.org/): Build automation tool.
