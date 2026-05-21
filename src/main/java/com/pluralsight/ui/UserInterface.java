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
    private Turtle turtle;
    private List<Shape> shapeList;

    public UserInterface() {
        this.shapeList = new ArrayList<>();
    }

    public void start(){
        homeScreen();
    }
    private void homeScreen() {
        int command;
        do {
            command=Console.promptForInt("""
                    1) Create New
                    2) Save Image
                    3) Save Painting (CSV)
                    4) Open Painting (CSV)
                    0) Exit
                    -> """,0,4);
            switch (command){
                case 1:
                    createNew();
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


    private void createNew() {
        System.out.println("Welcome! Create your World");
        this.world = createWorld();
        this.turtle = new Turtle(this.world,0,0);
        shapeList.clear();
        System.out.println("you are all set, start painting! ");
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

    private void processSquare() {
        int x = Console.promptForInt("Enter X coordinate for the shape: ");
        int y = Console.promptForInt("Enter Y coordinate for the shape: ");
        this.turtle.penUp();
        this.turtle.goTo(x,y);
        Point2D startingLocation = new Point2D.Double(x,y);
        Color color = Console.promptForColor("Select line color: ");
        int border = Console.promptForInt("Enter border thickness (sharpness): ", 1, 10);
        double sideLength =Console.promptForDouble("Enter the Square's Side Length: ");
        Shape square = new Square(this.turtle,startingLocation,color,border,sideLength);
        shapeList.add(square);
        square.paint();
        paintingLoop(square);

    }


    private void processHexagon() {
        int x = Console.promptForInt("Enter X coordinate for the shape: ");
        int y = Console.promptForInt("Enter Y coordinate for the shape: ");
        this.turtle.penUp();
        this.turtle.goTo(x,y);
        Point2D startingLocation = new Point2D.Double(x,y);
        Color color = Console.promptForColor("Select line color: ");
        int border = Console.promptForInt("Enter border thickness (sharpness): ", 1, 10);
        double sideLength =Console.promptForDouble("Enter the Hexagon's Side Length: ");
        Shape hexagon = new Hexagon(this.turtle,startingLocation,color,border,sideLength);
        shapeList.add(hexagon);
        hexagon.paint();
        paintingLoop(hexagon);
    }

    private void processTriangle() {
        int x = Console.promptForInt("Enter X coordinate for the shape: ");
        int y = Console.promptForInt("Enter Y coordinate for the shape: ");
        this.turtle.penUp();
        this.turtle.goTo(x,y);
        Point2D startingLocation = new Point2D.Double(x,y);
        Color color = Console.promptForColor("Select line color: ");
        int border = Console.promptForInt("Enter border thickness (sharpness): ", 1, 10);
        double sideLength =Console.promptForDouble("Enter the Triangle's Side Length: ");
        int command;
        boolean isEqualateral = false;
            command = Console.promptForInt("""
                    1) Right Triangle
                    2) Equilateral Triangle
                    -> """,1,2);
            isEqualateral = switch (command) {
                case 1 -> false;
                case 2 -> true;
                default -> isEqualateral;
            };


        Triangle triangle = new Triangle(this.turtle,startingLocation,color,border,sideLength,isEqualateral);
        shapeList.add(triangle);
        triangle.paint();
        paintingLoop(triangle);

    }

    private void processCircle() {
        int x = Console.promptForInt("Enter X coordinate for the shape: ");
        int y = Console.promptForInt("Enter Y coordinate for the shape: ");
        this.turtle.penUp();
        this.turtle.goTo(x,y);
        Point2D startingLocation = new Point2D.Double(x,y);
        Color color = Console.promptForColor("Select line color: ");
        int border = Console.promptForInt("Enter border thickness (sharpness): ", 1, 10);
        double radius =Console.promptForDouble("Enter the Circle's Radius Length: ");
        Shape circle = new Circle(this.turtle,startingLocation,color,border,radius);
        shapeList.add(circle);
        circle.paint();
        paintingLoop(circle);

    }
    private void paintingLoop(Shape s){
        int command;
        do{
            command = Console.promptForInt("""
                    1) Add Duplicate
                    0) Go Back
                    -> """,0,1);
            switch (command) {
                case 1:
                    int x = Console.promptForInt("Enter X coordinate for the shape: ");
                    int y = Console.promptForInt("Enter Y coordinate for the shape: ");
                    Shape newShape = s.clone();
                    newShape.setLocation(new Point2D.Double(x,y));
                    shapeList.add(newShape);
                    newShape.paint();
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

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/"+filename))){
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
            size = ((Square) s).getSide();
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
                BufferedReader reader = new BufferedReader(new FileReader("data/"+filename));
                String line;
                reader.readLine();
                String canvasData = reader.readLine();
                String[] canvasParts = canvasData.split("\\|");
                int width = Integer.parseInt(canvasParts[0]);
                int height = Integer.parseInt(canvasParts[1]);
                int backgroundRGB = Integer.parseInt(canvasParts[2]);
                Color background = new Color(backgroundRGB);
                this.world = new World(width, height, background);
                this.turtle = new Turtle(this.world,0,0);
                this.world.repaint();
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
                    this.turtle.penUp();
                    this.turtle.goTo(new Point2D.Double(x, y));
                    this.turtle.setHeading(0);
                    this.turtle.penDown();
                    Shape shape = null;

                    switch (type) {
                        case "square":
                            shape = new Square(this.turtle, new Point2D.Double(x, y), color, border, size);
                            shape.paint();
                            break;

                        case "circle":
                            shape = new Circle(this.turtle, new Point2D.Double(x, y), color, border, size);
                            shape.paint();
                            break;

                        case "right_triangle":
                            Triangle rt = new Triangle(this.turtle, new Point2D.Double(x, y), color, border, size,false);
                            rt.paint();
                            shape = rt;
                            break;

                        case "equilateral_triangle":
                            Triangle et = new Triangle(this.turtle, new Point2D.Double(x, y), color, border, size, true);
                            et.paint();
                            shape = et;
                            break;

                        case "hexagon":
                            shape = new Hexagon(this.turtle, new Point2D.Double(x, y), color, border, size);
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


