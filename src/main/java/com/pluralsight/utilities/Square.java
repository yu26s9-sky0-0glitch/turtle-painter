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

    @Override
    public void paint() {
        Turtle t = getTurtle();
        int side = 0;
        while(side<4){
            t.turnRight(90);
            t.forward(sideLength);
            side++;
        }
    }
}
