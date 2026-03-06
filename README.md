# Blackhole Sim 2D

A sleek 2D blackhole simulation desktop application built with Java and JavaFX, featuring a modern dark UI with custom title bar and styling.

## Prerequisites

- **Java Development Kit (JDK)** version 25 or higher
- **Gradle** (included via Gradle wrapper)
- **JavaFX** SDK (for local development)

## Installation

1. Clone the repository:
   ```bash
   git clone "https://github.com/ikanlelebakar/Java-Blackhole.git"
   cd Java-Blackhole
   ```

2. Build the project:
   ```bash
   ./gradlew build
   ```

3. Run the application:
   ```bash
   ./gradlew run
   ```

## Usage

- The application launches with a transparent window and custom title bar
- Drag the title bar to move the window
- Click the **X** button to close the application
- A blackhole visualization is displayed in the center of the window, with animated star particles moving across the scene

## Project Structure

```
Java-Blackhole/
├── .gitattributes
├── .gitignore
├── app/
│   ├── build.gradle.kts
│   └── src/main/
│       ├── java/com/blackhole/
│       │   ├── Main.java
│       │   ├── blackholeAssets.java
│       │   ├── starAssets.java
│       │   ├── customTitleBar.java
│       │   └── PhysicsHelper.java
│       └── resources/
│           ├── fonts/
│           │   └── JetBrainsMonoNerdFont-Regular.ttf
│           ├── icon.png
│           ├── iconBackup.png
│           └── style.css
├── gradle/
│   ├── libs.versions.toml
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── gradle.properties
├── README.md
└── settings.gradle.kts
```

## Features

- Custom frameless window with transparent background
- Draggable title bar
- Dark theme using JMetro library
- Custom JetBrains Mono font
- Modern UI styling with CSS
- Blackhole visualization with animated star particles and trail effects
- Separated component architecture: `blackholeAssets` for visualization and `starAssets` for particle animation
- Collision detection between stars and blackhole
- Clean separation of concerns with independent motion controllers
- Physics calculations handled by dedicated `PhysicsHelper` class
