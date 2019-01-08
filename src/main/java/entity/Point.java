package entity;

/**
 * Point in x-y
 *
 * Created by liuyuchen on 2018/5/25.
 */
public class Point {
    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Point() {}

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public boolean equel(Point p) {
        return (x == p.getX() && y == p.getY());
    }

    public String toString() {
        return "("+this.getX()+", "+this.getY()+")";
    }
}
