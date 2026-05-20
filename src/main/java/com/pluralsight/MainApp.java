package com.pluralsight;

import com.pluralsight.forms.Turtle;
import com.pluralsight.forms.World;
import com.pluralsight.utilities.Circle;
import com.pluralsight.utilities.Shape;
import com.pluralsight.utilities.Square;
import com.pluralsight.utilities.Triangle;

import java.awt.*;
import java.awt.geom.Point2D;

public class MainApp
{
    public static void main(String[] args) {
        World world = new World(600, 600,new Color(178, 197, 197));
        Turtle turtle = new Turtle(world,0,0);
        Point2D startingLocation = new Point2D.Double(-100,-100);
        Shape squareRed = new Square(turtle,startingLocation,Color.RED,5,80);
        Shape circleGreen = new Circle(turtle,startingLocation,Color.GREEN,3,40);
        Shape triangleYellow = new Triangle(turtle,startingLocation,Color.YELLOW,3,80);
        squareRed.paint();
        circleGreen.paint();
        triangleYellow.paint();

}}
