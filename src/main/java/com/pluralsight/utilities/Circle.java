package com.pluralsight.utilities;

import com.pluralsight.forms.Turtle;

import java.awt.*;
import java.awt.geom.Point2D;

public class Circle extends Shape{
    private double radius;

    public Circle(Turtle turtle, Point2D location, Color color, int border, double radius) {
        super(turtle, location, color, border);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public void paint() {
        Turtle t = getTurtle();
        t.penUp();
        t.goTo(getLocation().getX(),getLocation().getY());
        t.penDown();
        t.setColor(getColor());
        t.setPenWidth(getBorder());
        double stepSize = (2 * Math.PI * radius) / 36;

        int steps = 0;
        while (steps < 36) {
            t.forward(stepSize);
            t.turnRight(10); // Turn 10 degrees at a time
            steps++;
        }
    }
}
