package com.SCC.park.mina;


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
        System.out.println("getGpsLattude:"+byte2float(buffer,4));
        return byte2float(buffer,4);}// 4567th bits are lattude
    public float getGpsLongitude(){return byte2float(buffer,4);}// 891011th bits are Longitude
    public CarHandler(byte[] buffer) {
        this.buffer = buffer;
    }

    public int getType() {
        return buffer[2];
    }
    /**
     * byte2float
     *
     * @param b byte (at least four bytes)
     * @param index start position
     * @return float
     */
    public static float byte2float(byte[] b, int index) {
        int l;
        l = b[index + 0];
        l &= 0xff;
        l |= ((long) b[index + 1] << 8);
        l &= 0xffff;
        l |= ((long) b[index + 2] << 16);
        l &= 0xffffff;
        l |= ((long) b[index + 3] << 24);
        return Float.intBitsToFloat(l);
    }
}

