package org.example;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        JImageDisplay newDisplay = new JImageDisplay(100, 100);
        newDisplay.clearImage();
    }
}
