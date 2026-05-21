package com.pluralsight.utilities;

import com.pluralsight.forms.Turtle;

import java.awt.*;
import java.awt.geom.Point2D;

public class Triangle extends Shape{
    private double side;
    private boolean isEquilateral;

    public Triangle(Turtle turtle, Point2D location, Color color, int border, double side, boolean isEquilateral) {
        super(turtle, location, color, border);
        this.side = side;
        this.isEquilateral = isEquilateral;
    }
    public double getSide() {
        return side;
    }

    public void setSide(double side) {
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
       if(this.isEquilateral){
           Turtle t = getTurtle();
           t.penUp();
           t.goTo(getLocation().getX(),getLocation().getY());
           t.penDown();
           t.setHeading(0);
           t.setColor(getColor());
           t.setPenWidth(getBorder());
           int countSide = 0;
           while (countSide<3){
               t.forward(side);
               t.turnLeft(120);
               countSide++;
           }
       }
       else{
           Turtle t = getTurtle();
           double hypotenuse = calculateHypotenuse();
           t.penUp();
           t.goTo(getLocation().getX(),getLocation().getY());
           t.penDown();
           t.setHeading(0);
           t.setColor(getColor());
           t.setPenWidth(getBorder());
           t.forward(side);
           t.turnLeft(90);
           t.forward(side);
           t.turnLeft(135);
           t.forward(hypotenuse);
       }


    }

    @Override
    public Shape clone() {
        return new Triangle(this.getTurtle(), this.getLocation(), this.getColor(), this.getBorder(), this.side,this.isEquilateral);
    }

    public boolean isEquilateral() {
        return isEquilateral;
    }

    public void setEquilateral(boolean equilateral) {
        isEquilateral = equilateral;
    }


}
