package org.example;

import java.text.DecimalFormat;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Point2d myPoint = new Point2d ();//создает точку (0,0)
        Point2d myOtherPoint = new Point2d (5,3);//создает точку (5,3)
        Point2d aThirdPoint = new Point2d ();
        Point3D fPoint = new Point3D();
        Point3D sPoint = new Point3D(1, 1, 1);
        DecimalFormat df = new DecimalFormat(".##");
        System.out.println(df.format(fPoint.distanceTo(sPoint)));

    }
}
