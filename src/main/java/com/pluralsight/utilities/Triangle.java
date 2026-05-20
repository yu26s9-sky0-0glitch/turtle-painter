package com.pluralsight.utilities;

import com.pluralsight.forms.Turtle;

import java.awt.*;
import java.awt.geom.Point2D;

public class Triangle extends Shape{
    private double side;

    public Triangle(Turtle turtle, Point2D location, Color color, int border, double side) {
        super(turtle, location, color, border);
        this.side = side;
    }

    public double getAngle() {
        return side;
    }

    public void setAngle(double side) {
        this.side = side;
    }

    private double calculateHypotenuse() {
        double widthSquared = Math.pow(side, 2);
        double heightSquared = Math.pow(side, 2);
        return Math.sqrt(widthSquared + heightSquared);
    }

    /**
     * going to paint a Right triangle
     */
    @Override
    public void paint() {

        Turtle t = getTurtle();
        double hypotenuse = calculateHypotenuse();
        t.penUp();
        t.goTo(getLocation().getX(),getLocation().getY());
        t.penDown();
        t.setColor(getColor());
        t.setPenWidth(getBorder());
        t.forward(side);
        t.turnLeft(90);
        t.forward(side);
        t.turnLeft(135);
        t.forward(hypotenuse);

    }

    /**
     * going to paint Equilateral triangle
     */
    public void paintEquilateral(){
        Turtle t = getTurtle();
        t.penUp();
        t.goTo(getLocation().getX(),getLocation().getY());
        t.penDown();
        t.setColor(getColor());
        t.setPenWidth(getBorder());
        int countSide = 0;
        while (countSide<3){
            t.forward(side);
            t.turnRight(120);
            countSide++;
        }
    }
}
