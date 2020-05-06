package org.example;
import java.util.Objects;
public class Location
{
    public int xCoord;
    public int yCoord;
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }
    public boolean equals(Object sLocation) {
        Location sLoc = (Location) sLocation;
        return (xCoord == sLoc.xCoord && yCoord == sLoc.yCoord);
    }
    public int hashCode() {
        return Objects.hash(xCoord, yCoord);
    }
    public Location()
    {
        this(0, 0);
    }
}