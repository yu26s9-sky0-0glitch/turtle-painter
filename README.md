# 🐢 Turtle Painter

Turtle Painter is an interactive, object-oriented Java application that allows users to create complex geometric art using a custom Turtle Graphics engine. Users can draw shapes via a command-line interface, duplicate and stack them, save their mathematical coordinates to CSV files, and export their final masterpieces as high-quality PNG images.

## ✨ Features
* **Interactive CLI Canvas:** Set your canvas size, background color, and start drawing.
* **Object-Oriented Shapes:** Supports dynamically rendered Squares, Circles, Hexagons, and Triangles (Equilateral and Right).
* **State Management:** Save your current painting to a `.csv` file to resume or edit later.
* **Image Export:** Render your canvas directly to a `.png` file.
* **Coordinate Math:** Operates on a Cartesian coordinate plane with `(0,0)` at dead center.

---

## 📁 Project Structure

The project is built using a clean MVC-style architecture, separating the graphical engine, user interface, and shape logic.

```text
turtle-painter/
├── data/                       # Contains all saved state files and exported art
│   ├── images/                 # Rendered .png exports
│   │   ├── cat.png
│   │   ├── rug.png
│   │   └── tunnel.png
│   ├── cat.csv                 # CSV coordinate data for the Geometric Cat
│   ├── rug.csv                 # CSV coordinate data for the Afghan Rug
│   └── tunnel.csv              # CSV coordinate data for the 3D Moire Tunnel
├── src/main/java/com/pluralsight/
│   ├── forms/                  # The core drawing engine
│   │   ├── Turtle.java         # The pen that draws on the screen
│   │   └── World.java          # The visual canvas window
│   ├── ui/                     # User Interaction layer
│   │   ├── Console.java        # Helper class for handling CLI inputs
│   │   └── UserInterface.java  # Main application loop and menu routing
│   ├── utilities/              # Object-Oriented Shape hierarchy
│   │   ├── Shape.java          # Abstract base class for all geometric objects
│   │   ├── Circle.java
│   │   ├── Hexagon.java
│   │   ├── Square.java
│   │   └── Triangle.java
│   └── MainApp.java            # Application Entry Point
```
## 🖼️ The Gallery (Included Artwork)

Included in the `data/` folder are three highly detailed mathematical art pieces generated entirely through precise CSV coordinate mapping. You can load these natively in the app!

1. **The Geometric Cat (`cat.csv` / `cat.png`)**
   A study in coordinate alignment, utilizing overlapping squares, circles, and triangles to build a perfectly proportioned, chunky geometric cat sitting in the center of the canvas.
2. **The Afghan Rug (`rug.csv` / `rug.png`)**
   A mathematically perfect, 200+ line replica of a traditional woven Afghan Rug. It features a deep madder red and indigo color palette, concentric border guards, and a perfectly aligned diamond-grid medallion layout.
3. **The Infinity Corridor (`tunnel.csv` / `tunnel.png`)**
   A geometric study in off-center perspective. By generating tens of concentric squares that systematically shrink and shift their center points toward the top-right quadrant, this pattern plays with the brain's understanding of vanishing points to create the striking illusion of a deep, 3D rectangular tunnel.
---

## 🚀 How to Use

### 1. Starting a Painting
Run `MainApp.java` to launch the application. You will be greeted by the Main Menu:
* Choose **Create New** to instantiate a blank `World` canvas.
* You will be prompted to enter a Width, Height, and Background Color.

### 2. Drawing Shapes
* Select a shape from the menu.
* **Coordinate System:** The center of your screen is `X = 0, Y = 0`. 
  * Positive `Y` goes **UP**, negative `Y` goes **DOWN**.
  * Positive `X` goes **RIGHT**, negative `X` goes **LEFT**.
* Follow the prompts to enter your coordinates, border thickness, color, and size.

### 3. Duplicating & Stacking
After drawing a shape, you will enter the `paintingLoop`. Here, you can rapidly stamp duplicates of your last shape by simply entering new `X` and `Y` coordinates, allowing you to build dense, complex patterns efficiently.

### 4. Saving & Loading
* **Save Painting (CSV):** Serializes your current shape list to a `.csv` file in the `data/` folder so you don't lose your work.
* **Open Painting (CSV):** Loads a previously saved `.csv` and dynamically redraws it onto the canvas. Try loading `rug.csv` to see it in action!
* **Save Image:** Captures the current `World` canvas and exports it as a `.png` to `data/images/`.

---

## 🛠️ Built With

* **Java** (JDK 17+)
* Custom Java AWT/Swing Canvas Framework