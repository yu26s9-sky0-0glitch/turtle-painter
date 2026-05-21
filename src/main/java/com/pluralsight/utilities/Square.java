package com.pluralsight.utilities;

import com.pluralsight.forms.Turtle;

import java.awt.*;
import java.awt.geom.Point2D;

public class Square extends Shape{
    private double side;

    public Square(Turtle turtle, Point2D location, Color color, int border, double side) {
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
     * paints a square
     */
    @Override
    public void paint() {
        Turtle t = getTurtle();
        t.penUp();
        t.goTo(getLocation().getX(),getLocation().getY());
        t.penDown();
        t.setHeading(0);
        t.setColor(getColor());
        t.setPenWidth(getBorder());
        int sidecount = 0;
        while(sidecount<4){
            t.forward(side);
            t.turnRight(90);
            sidecount++;
        }
    }

    @Override
    public Shape clone() {
        return new Square(this.getTurtle(), this.getLocation(), this.getColor(), this.getBorder(), this.side);
    }
}
