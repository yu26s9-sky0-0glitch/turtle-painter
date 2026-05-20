package com.pluralsight.utilities;

import com.pluralsight.forms.Turtle;

import java.awt.*;
import java.awt.geom.Point2D;

public class Square extends Shape{
    private double sideLength;

    public Square(Turtle turtle, Point2D location, Color color, int border, double sideLength) {
        super(turtle, location, color, border);
        this.sideLength = sideLength;
    }

    public double getSideLength() {
        return sideLength;
    }

    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    /**
     * paints a square
     */
    @Override
    public void paint() {
        Turtle t = getTurtle();
        t.penUp();
        t.goTo(getLocation().getX(),getLocation().getY());
        t.penDown();
        t.setColor(getColor());
        t.setPenWidth(getBorder());
        int side = 0;
        while(side<4){
            t.forward(sideLength);
            t.turnRight(90);
            side++;
        }
    }
}
