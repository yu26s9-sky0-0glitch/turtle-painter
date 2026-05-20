package com.pluralsight.utilities;
import com.pluralsight.forms.Turtle;
import com.pluralsight.forms.World;

import java.awt.*;
import java.awt.geom.Point2D;


public abstract class Shape {
    private Turtle turtle;
    private Point2D location;
    private Color color;
    private int border;

    public Shape(Turtle turtle, Point2D location, Color color, int border) {
        this.turtle = turtle;
        this.location = location;
        this.color = color;
        this.border = border;
    }



    public Turtle getTurtle() {
        return turtle;
    }

    public void setTurtle(Turtle turtle) {
        this.turtle = turtle;
    }

    public Point2D getLocation() {
        return location;
    }

    public void setLocation(Point2D location) {
        this.location = location;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getBorder() {
        return border;
    }

    public void setBorder(int border) {
        this.border = border;
    }
    public abstract void paint();
}
