package org.example;

import java.util.*;

public class AStarState
{
    private Map2D map;
    private HashMap<Location, Waypoint> openWaypoints = new HashMap<Location, Waypoint> ();

    private HashMap<Location, Waypoint> closedWaypoints = new HashMap<Location, Waypoint> ();

    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    public Map2D getMap()
    {
        return map;
    }

    public Waypoint getMinOpenWaypoint()
    {
        float value = Float.MAX_VALUE;
        Waypoint wp = null;
        for (Map.Entry<Location, Waypoint> waypoint : openWaypoints.entrySet()) {
            if(waypoint.getValue().getTotalCost() < value) {
                value = waypoint.getValue().getTotalCost();
                wp = waypoint.getValue();
            }
        }
        return wp;
    }


    public boolean addOpenWaypoint(Waypoint newWP)
    {
        Location newLoc = newWP.getLocation();
        if (!this.openWaypoints.containsKey(newLoc)) {
            openWaypoints.put(newLoc, newWP);
            return true;
        } else {
            Waypoint curWP = openWaypoints.get(newLoc);
            if (newWP.getPreviousCost() < curWP.getPreviousCost()) {
                openWaypoints.put(newLoc, newWP);
                return true;
            } else {
                return false;
            }
        }
    }


    public int numOpenWaypoints()
    {
        return openWaypoints.size();
    }


    public void closeWaypoint(Location loc)
    {
        Waypoint mvWP = openWaypoints.remove(loc);
        closedWaypoints.put(loc, mvWP);
    }

    public boolean isLocationClosed(Location loc)
    {
        return closedWaypoints.containsKey(loc);
    }
}
