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

## Project Structure

```
Java-Blackhole/
├── .git/
├── .gitattributes
├── .gitignore
├── .gradle/
├── app/
│   ├── build.gradle.kts
│   └── src/main/
│       ├── java/com/blackhole/
│       │   ├── Main.java
│       │   └── CustomTitleBar.java
│       └── resources/
│           ├── fonts/
│           │   └── JetBrainsMonoNerdFont-Regular.ttf
│           ├── icon.png
│           ├── iconBackup.png
│           └── style.css
├── build/
├── gradle/
├── gradlew
├── gradlew.bat
├── gradle.properties
└── settings.gradle.kts
```

## Features

- Custom frameless window with transparent background
- Draggable title bar
- Dark theme using JMetro library
- Custom JetBrains Mono font
- Modern UI styling with CSS
