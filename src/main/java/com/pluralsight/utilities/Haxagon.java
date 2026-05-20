package com.pluralsight.utilities;

import com.pluralsight.forms.Turtle;

import java.awt.*;
import java.awt.geom.Point2D;

public class Haxagon extends Shape{
    private double side;

    public Haxagon(Turtle turtle, Point2D location, Color color, int border, double side) {
        super(turtle, location, color, border);
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    /**
     * paints a hexagon
     */
    @Override
    public void paint() {
        Turtle t = getTurtle();
        t.penUp();
        t.goTo(getLocation().getX(),getLocation().getY());
        t.penDown();
        t.setColor(getColor());
        t.setPenWidth(getBorder());
        int sidecount = 0;
        while(sidecount<6){
            t.forward(side);
            t.turnRight(60);
            sidecount++;
        }
    }
}
