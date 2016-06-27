package com.SCC.park.mina;


import com.SCC.park.utils.Utils;

public class CarHandler {

    protected byte[] buffer;
    public int getId(){
        return 4;//return  ID
        // return buffer[2];
    }
    public int getCommandType() {
        return buffer[2];
    }
    public int getData() {
        return buffer[3];
    }
    public float getGpsLattude(){
        System.out.println("getGpsLattude:"+ Utils.byte2float(buffer,4));
        return Utils.byte2float(buffer,4);}// 4567th bits are lattude
    public float getGpsLongitude(){return Utils.byte2float(buffer,4);}// 891011th bits are Longitude
    public CarHandler(byte[] buffer) {
        this.buffer = buffer;
    }

    public int getType() {
        return buffer[2];
    }

}

