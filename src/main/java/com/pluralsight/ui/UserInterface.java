package com.pluralsight.ui;

import com.pluralsight.forms.Turtle;
import com.pluralsight.forms.World;
import com.pluralsight.utilities.*;
import com.pluralsight.utilities.Shape;
import java.awt.*;
import java.awt.geom.Point2D;

public class UserInterface {
    private World world;
    public void start(){
        System.out.println("Welcome! Create your World");
        this.world = createWorld();
        System.out.println("you are all set, start painting! ");
        homeScreen();
    }
    private World createWorld() {
        int width = Console.promptForInt("Enter the width: ");
        int height = Console.promptForInt("Enter the height: ");
        Color color = Console.promptForColor("Set the background color: ");
        World w =  new World(width,height,color);
        return w;

    }
    private void homeScreen() {
        int command;
        do {
            command=Console.promptForInt("""
                    1) Add Shape
                    2) Save Image
                    0) Exit
                    -> """,0,2);
            switch (command){
                case 1:
                    addShape();
                    break;
                case 2:
                    saveImage();
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
        paintingLoop(square);

    }


    private void processHexagon() {
        Turtle t = ProcessTurtle();
        Point2D startingLocation = t.getLocation();
        Color color = Console.promptForColor("Select line color: ");
        int border = Console.promptForInt("Enter border thickness (sharpness): ", 1, 10);
        double sideLength =Console.promptForDouble("Enter the Hexagon's Side Length: ");
        Shape hexagon = new Haxagon(t,startingLocation,color,border,sideLength);
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
                    break;
                case 2:
                    triangle.paintEquilateral();
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
        paintingLoop(circle);

    }
    private void paintingLoop(Shape s){
        int command;
        do{
            s.paint();
            command = Console.promptForInt("""
                    1) Add
                    0) Go Back
                    -> """,0,1);
            switch (command){
                case 1:
                    int x = Console.promptForInt("Enter X coordinate for the shape: ");
                    int y = Console.promptForInt("Enter Y coordinate for the shape: ");
                    s.setLocation(new Point2D.Double(x,y));
                    s.paint();
                    break;
            }
        }while(command!=0);
    }
    private void saveImage() {
        String name = Console.promptForString("Enter the name of the file: ");
        world.saveAs(name+".png","data/images");
    }
}
