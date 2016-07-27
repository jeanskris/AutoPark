package com.SCC.park.model;

import org.springframework.stereotype.Repository;

/**
 * Created by ZJDX on 2016/7/25.
 */
@Repository
public class Trip {
    private int MapID;
    private Coordinate startPoint;
    private Coordinate endPoint;
    public int getMapID() {
        return MapID;
    }

    public void setMapID(int mapID) {
        MapID = mapID;
    }
    public Coordinate getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Coordinate endPoint) {
        this.endPoint = endPoint;
    }

    public Coordinate getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Coordinate startPoint) {
        this.startPoint = startPoint;
    }

    public String toString(){
        return  "mapID:"+this.getMapID()+"startPoint:"+startPoint.getX()+" "+startPoint.getY()+" endPoint:"+endPoint.getX()+" "+endPoint.getY();

    }
}
