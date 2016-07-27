package com.SCC.park;

import com.SCC.park.mina.SessionMap;
import com.SCC.park.mina.TcpClient;
import com.SCC.park.utils.Constant;
import com.SCC.park.utils.Utils;
import com.SCC.park.model.HttpSelfdefinedRequest;
import org.apache.mina.core.buffer.IoBuffer;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by ZJDX on 2016/6/20.
 */
@Service
public class CarService {
    private static Logger logger = LoggerFactory.getLogger(CarService.class);
    public void sendPath2Car(){
        TcpClient tcpClient=new TcpClient();
        byte[] bytes = { 0x11};
        tcpClient.send(IoBuffer.wrap(bytes));
    }
    public byte[] getDataFromCar(byte[] bytes){
        byte[] data=bytes;
        if(data[2]==0x04) {
            Constant.TIMES = data[4];
            System.out.println("Times:" + Constant.TIMES);
        }
        return data;
    }
    public boolean sendToCar(byte[] bytes){
        TcpClient tcpClient=new TcpClient();
        byte[] data = bytes;
        return tcpClient.send(IoBuffer.wrap(data));
    }
    public void sendCommand(byte[] message){
        byte[] bytes = message;
        bytes= Utils.checkData(bytes);
        SessionMap sessionMap = SessionMap.newInstance();
        sessionMap.send(Constant.TCP_REMOTE_SERVER_IP, IoBuffer.wrap(bytes));
        System.out.println("bytes sendCommand:"+IoBuffer.wrap(bytes));
        logger.debug("bytes sendCommand:"+IoBuffer.wrap(bytes));
    }
    /**
     * replaceByte
     * replaced the byte at specified index by byte b
     * @param  data:first_param original data array;
     * @param  index: second_param the position of the byte need to replace;
     * @param   b: third_param new data;
     * @return command bytes array
     * */
    public  byte[] replaceByte(byte[] data,int index,byte b){
        byte[] after=data;
        after[index]=b;
        return after;
    }
    public void sendCommandToRemote(String action){
        logger.debug("sendCommandToRemote");
        HttpSelfdefinedRequest hr=new HttpSelfdefinedRequest();
        JSONObject result=hr.sendGet("http://localhost:8081/"+action,"");
    }
}
