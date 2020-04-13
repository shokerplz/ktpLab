package org.example;
public class Point3D {
    private double xCoord;
    private double yCoord;
    private double zCoord;
    public Point3D(double x, double y, double z) {
        xCoord = x;
        yCoord = y;
        zCoord = z;
    }
    public Point3D() {
        xCoord = 0;
        yCoord = 0;
        zCoord = 0;
    }
    public double getX () {
        return xCoord;
    }
    public double getY () {
        return yCoord;
    }
    public double getZ () {
        return zCoord;
    }
    public void setX ( double val) {
        xCoord = val;
    }
    public void setY ( double val) {
        yCoord = val;
    }
    public void setZ ( double val) {
        zCoord = val;
    }
    public boolean equal (Point3D otherPoint) { return (xCoord == otherPoint.getX() && yCoord == otherPoint.getY() && zCoord == otherPoint.getZ());}
    public double distanceTo (Point3D otherPoint) {
        double x1 = otherPoint.getX();
        double y1 = otherPoint.getY();
        double z1 = otherPoint.getZ();
        return Math.sqrt(Math.pow(x1 - xCoord, 2) + Math.pow(y1 - yCoord, 2) + Math.pow(z1 - zCoord, 2));
    }
}
