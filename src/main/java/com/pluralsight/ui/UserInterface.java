package com.pluralsight.ui;

import com.pluralsight.forms.Turtle;
import com.pluralsight.forms.World;
import com.pluralsight.utilities.*;
import com.pluralsight.utilities.Shape;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.geom.Point2D;

public class UserInterface {
    private World world;
    private List<Shape> shapeList;

    public UserInterface() {
        this.shapeList = new ArrayList<>();
    }

    public void start(){
        System.out.println("Welcome! Create your World");
        this.world = createWorld();
        System.out.println("you are all set, start painting! ");
        homeScreen();
    }
    private void homeScreen() {
        int command;
        do {
            command=Console.promptForInt("""
                    1) Add Shape
                    2) Save Image
                    3) Save Painting (CSV)
                    4) Open Painting (CSV)
                    0) Exit
                    -> """,0,4);
            switch (command){
                case 1:
                    addShape();
                    break;
                case 2:
                    saveImage();
                    break;
                case 3:
                    savePainting();
                    break;
                case 4:
                    openPainting();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input! Try again!");
                    break;
            }
        }while (command!=0);
    }

    private World createWorld() {
        int width = Console.promptForInt("Enter the width: ");
        int height = Console.promptForInt("Enter the height: ");
        Color color = Console.promptForColor("Set the background color: ");
        World w =  new World(width,height,color);
        return w;

    }


    private void addShape() {
        int shape;
        do{
            shape = Console.promptForInt("""
                    1. square
                    2. circle
                    3. triangle
                    4. hexagon
                    0. Exit
                    -> """);
            switch (shape){
                case 1:
                    processSquare();
                    break;
                case 2:
                    processCircle();
                    break;
                case 3:
                    processTriangle();
                    break;
                case 4:
                    processHexagon();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid input! Try again!");
            }
        }while(shape!=0);

    }
    private Turtle ProcessTurtle() {
        int x = Console.promptForInt("Enter X coordinate for the shape: ");
        int y = Console.promptForInt("Enter Y coordinate for the shape: ");
        return  new Turtle(this.world, x, y);
    }

    private void processSquare() {
       Turtle t = ProcessTurtle();
        Color color = Console.promptForColor("Select line color: ");
        int border = Console.promptForInt("Enter border thickness (sharpness): ", 1, 10);
        double sideLength =Console.promptForDouble("Enter the Square's Side Length: ");
        Point2D startingLocation = t.getLocation();
        Shape square = new Square(t,startingLocation,color,border,sideLength);
        shapeList.add(square);
        paintingLoop(square);

    }


    private void processHexagon() {
        Turtle t = ProcessTurtle();
        Point2D startingLocation = t.getLocation();
        Color color = Console.promptForColor("Select line color: ");
        int border = Console.promptForInt("Enter border thickness (sharpness): ", 1, 10);
        double sideLength =Console.promptForDouble("Enter the Hexagon's Side Length: ");
        Shape hexagon = new Hexagon(t,startingLocation,color,border,sideLength);
        shapeList.add(hexagon);
        paintingLoop(hexagon);
    }

    private void processTriangle() {
        Turtle t = ProcessTurtle();
        Point2D startingLocation = t.getLocation();
        Color color = Console.promptForColor("Select line color: ");
        int border = Console.promptForInt("Enter border thickness (sharpness): ", 1, 10);
        double sideLength =Console.promptForDouble("Enter the Triangle's Side Length: ");
        Triangle triangle = new Triangle(t,startingLocation,color,border,sideLength);
        int command;
        do{
            command = Console.promptForInt("""
                    1) Right Triangle
                    2) Equilateral Triangle
                    0) Go Back
                    -> """,0,2);
            switch (command){
                case 1:
                    triangle.paint();
                    shapeList.add(triangle);
                    break;
                case 2:
                    triangle.paintEquilateral();
                    shapeList.add(triangle);
                    break;
            }
        }while(command!=0);
    }

    private void processCircle() {
        Turtle t = ProcessTurtle();
        Point2D startingLocation = t.getLocation();
        Color color = Console.promptForColor("Select line color: ");
        int border = Console.promptForInt("Enter border thickness (sharpness): ", 1, 10);
        double radius =Console.promptForDouble("Enter the Circle's Radius Length: ");
        Shape circle = new Circle(t,startingLocation,color,border,radius);
        shapeList.add(circle);
        paintingLoop(circle);

    }
    private void paintingLoop(Shape s){
        s.paint();
        int command;
        do{
            command = Console.promptForInt("""
                    1) Add Duplicate
                    0) Go Back
                    -> """,0,1);
            switch (command){
                case 1:
                    int x = Console.promptForInt("Enter X coordinate for the shape: ");
                    int y = Console.promptForInt("Enter Y coordinate for the shape: ");
                    s.setLocation(new Point2D.Double(x,y));
                    shapeList.add(s);
                    s.paint();
                    break;
            }
        }while(command!=0);
    }
    private void saveImage() {
        String name = Console.promptForString("Enter the name of the file: ");
        world.saveAs(name+".png","data/images");

    }

    private void savePainting() {
        String filename = Console.promptForString("Enter filename to save (e.g., painting.csv): ");
        if (!filename.endsWith(".csv")) filename += ".csv";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write("width|height|background\n");
            writer.write(world.getWidth() + "|" + world.getHeight() +"|"+ world.getBackground().getRGB());
            writer.newLine();
            writer.write("shape|x|y|border|color|size\n");
            for (Shape s : shapeList) {
                String dataLine = getString(s);
                writer.write(dataLine);
                writer.newLine();
            }

            System.out.println("Painting saved successfully to " + filename);
            writer.close();
        } catch (IOException e) {
            System.err.println("Strict Error Caught! Could not save file: " + e.getMessage());
        }
    }

    private static String getString(Shape s) {
        String type = s.getClass().getSimpleName().toLowerCase();
        if (s instanceof Triangle) {
            if (((Triangle) s).isEquilateral()) {
                type = "equilateral_triangle";
            } else{
                type = "right_triangle";
            }}

        int x = (int) s.getLocation().getX();
        int y = (int) s.getLocation().getY();
        int border = s.getBorder();
        int colorCode = s.getColor().getRGB();

        double size = 0;
        if (s instanceof Circle) {
            size = ((Circle) s).getRadius();
        } else if (s instanceof Square) {
            size = ((Square) s).getSideLength();
        }else if (s instanceof Triangle) {
            size = ((Triangle)s).getSide();
        }else if (s instanceof Hexagon){
            size = ((Hexagon) s).getSide();
        }
        String dataLine = type + "|" + x + "|" + y + "|" + border + "|" + colorCode + "|" + size;
        return dataLine;
    }

    private void openPainting(){
            String filename = Console.promptForString("Enter the CSV filename to load: ");

            try {
                BufferedReader reader = new BufferedReader(new FileReader(filename));
                String line;

                // Read Canvas Header & Data
                reader.readLine(); // skips the "width|height|background" header line
                String canvasData = reader.readLine();
                String[] canvasParts = canvasData.split("\\|");
                int width = Integer.parseInt(canvasParts[0]);
                int height = Integer.parseInt(canvasParts[1]);
                int backgroundRGB = Integer.parseInt(canvasParts[2]);
                Color background = new Color(backgroundRGB);
                this.world = new World(width, height, background);
                shapeList.clear();

                reader.readLine();


                while ((line = reader.readLine()) != null) {
                    if (line.trim().isEmpty()) continue;
                    String[] p = line.split("\\|");

                    String type = p[0];
                    int x = Integer.parseInt(p[1]);
                    int y = Integer.parseInt(p[2]);
                    int border = Integer.parseInt(p[3]);
                    int colorRGB = Integer.parseInt(p[4]);
                    Color color = new Color(colorRGB);
                    double size = Double.parseDouble(p[5]);

                    Turtle t = new Turtle(this.world, x, y);
                    Shape shape = null;

                    switch (type) {
                        case "square":
                            shape = new Square(t, new Point2D.Double(x, y), color, border, size);
                            shape.paint();
                            break;

                        case "circle":
                            shape = new Circle(t, new Point2D.Double(x, y), color, border, size);
                            shape.paint();
                            break;

                        case "right_triangle":
                            Triangle rt = new Triangle(t, new Point2D.Double(x, y), color, border, size);
                            rt.paint();
                            shape = rt;
                            break;

                        case "equilateral_triangle":
                            Triangle et = new Triangle(t, new Point2D.Double(x, y), color, border, size);
                            et.paintEquilateral();
                            shape = et;
                            break;

                        case "hexagon":
                            shape = new Hexagon(t, new Point2D.Double(x, y), color, border, size);
                            shape.paint();
                            break;
                    }

                    if (shape != null) {
                        shapeList.add(shape);
                    }}
                    System.out.println("Painting loaded successfully!");
                    reader.close();

            } catch (IOException e) {
                System.err.println("Strict Error Caught! Could not read file: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Data Parsing Error (Bad CSV formatting): " + e.getMessage());
            }
        }

    }


