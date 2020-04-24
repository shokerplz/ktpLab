package org.example;

import java.util.Scanner;

public class Lab1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите x: "); double x0 = scan.nextDouble();
        System.out.println("Введите y: "); double y0 = scan.nextDouble();
        System.out.println("Введите z: "); double z0 = scan.nextDouble();
        System.out.println("Введите x: "); double x1 = scan.nextDouble();
        System.out.println("Введите y: "); double y1 = scan.nextDouble();
        System.out.println("Введите z: "); double z1 = scan.nextDouble();
        System.out.println("Введите x: "); double x2 = scan.nextDouble();
        System.out.println("Введите y: "); double y2 = scan.nextDouble();
        System.out.println("Введите z: "); double z2 = scan.nextDouble();
        Point3D fPoint = new Point3D(x0, y0, z0);
        Point3D sPoint = new Point3D(x1, y1, z1);
        Point3D tPoint = new Point3D(x2, y2, z2);
        if(fPoint.equal(sPoint) || sPoint.equal(tPoint) || tPoint.equal(fPoint)) {
            System.out.println("Одна или несколько точек сопадают");
        } else {
            System.out.println(computeArea(fPoint, sPoint, tPoint));
        }
    }
    public static double computeArea(Point3D fPoint, Point3D sPoint, Point3D tPoint) {
        double ab = fPoint.distanceTo(sPoint);
        double bc = sPoint.distanceTo(tPoint);
        double ac = tPoint.distanceTo(fPoint);
        double p = .5*(ab+bc+ac);
        return (Math.sqrt(p*(p-ab)*(p-bc)*(p-ac)));
    }
}
