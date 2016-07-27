package com.SCC.park.controller;

import com.SCC.park.CarService;
import com.SCC.park.LocateService;
import com.SCC.park.mina.TCPServerHandler;
import com.SCC.park.model.HttpSelfdefinedRequest;
import com.SCC.park.model.Map;
import com.SCC.park.model.ResponseMessage;
import com.SCC.park.model.Trip;
import com.SCC.park.sensor.PerceptService;
import com.SCC.park.utils.Constant;
import com.SCC.park.utils.ServerConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ZJDX on 2016/6/25.
 */
@RestController
public class Action {
    private static Logger logger = LoggerFactory.getLogger(Action.class);

    @Autowired
    LocateService locateService;
    @Autowired
    CarService carService;
    @Autowired
    TCPServerHandler tcpServerHandler;
    @Autowired
    PerceptService perceptService;
    @RequestMapping(value = "/startAutoPark", method = RequestMethod.GET)
    public void startAutoPark(){
        logger.debug("run");
        carService.sendCommand(Constant.RUN);
    }
    @RequestMapping(value = "/returnRoad", method = RequestMethod.GET)
    public void returnRoad(){
        logger.debug("returnRoad");
        carService.replaceByte(Constant.FORWARD,5,(byte)0x0a);//前进距离设置为10
        carService.replaceByte(Constant.FORWARD,6,(byte)0x02);//前进距离设置为2
        carService.sendCommand(Constant.FORWARD);//发送前进指令
        carService.sendCommand(Constant.RUN);
    }
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public  ResponseMessage update(){
        ResponseMessage msg=new ResponseMessage();
        int b=Constant.TIMES;
        msg.setMessage(Integer.toString(b));
        return msg;
    }
    @RequestMapping(value = "/forward", method = RequestMethod.GET)
    public void forward(){
        logger.debug("forward");
        carService.sendCommand(Constant.FORWARD);
        try {
            Thread.sleep(200);
        }catch (Exception e){
        System.out.println(e.getMessage().toString());
        }
    }
    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    public void stop(){
        logger.debug("stop");
        carService.sendCommand(Constant.STOP);
    }
    @RequestMapping(value = "/turnLeft", method = RequestMethod.GET)
    public void turnLeft(){
        logger.debug("turnLeft");
        carService.sendCommand(Constant.TURN_LEFT);
    }

    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public void clear(){
        logger.debug("clear");
        carService.sendCommand(Constant.CLEAR);
    }
    @RequestMapping(value = "/fellowMagnetic", method = RequestMethod.GET)
    public void fellowMagnetic(){
        logger.debug("fellowMagnetic");
        carService.sendCommand(Constant.FOLLOW_MAGNETIC);
    }
    @RequestMapping(value = "/previousPage", method = RequestMethod.GET)
    public String previousPage(){
        //return "http://10.214.143.78:8081/index.html";

        return "http://114.215.144.107:8080/infoplatform/";
    }

    @RequestMapping(value = "/startByPlatform", method = RequestMethod.GET)
    public void startByPlatform(){
        logger.debug("startByPlatform");
        HttpSelfdefinedRequest hr=new HttpSelfdefinedRequest();
        String result="";
        System.out.println(result);
    }


    @RequestMapping(value = "/getDataFromPlatform", method = RequestMethod.GET)
    public String getDataFromPlatform(){
        logger.debug("getDataFromPlatform");
        HttpSelfdefinedRequest hr=new HttpSelfdefinedRequest();
        String result="";
        System.out.println(result);
        return result;
    }
    @RequestMapping(value = "/getMap", method = RequestMethod.GET)
    public Map getMap(@RequestParam(value="mapId", required=false) Integer id) {
        System.out.println("mapId:" + id);
        HttpSelfdefinedRequest hr=new HttpSelfdefinedRequest();
        Map map=new Map();
        try {
            JSONObject result=hr.sendGet(ServerConfig.ENVIRONMENT_SERVER+"getMap?mapId="+id,"");
            ObjectMapper mapper=new ObjectMapper();
            map= mapper.readValue(result.toString(),Map.class);
        }catch (Exception e){
            e.printStackTrace();
        }

        return map;
    }
//获取前台设置的起始点和终点，将路径发送给车服务器生成路径并解析成指令
    @RequestMapping(value = "/createTrip", method = RequestMethod.POST)
    public void mouseClickPoint(@RequestBody JSONObject tr) {
        try {

            ObjectMapper mapper = new ObjectMapper();
            Trip trip = mapper.readValue(tr.toString(), Trip.class);
            System.out.println("path:" + trip.getStartPoint().toString() + "," + trip.getEndPoint().toString()+" mapID:"+trip.getMapID());
            JSONObject jsonObject= HttpSelfdefinedRequest.sendPost(ServerConfig.SMARTCAR_SERVER+"startAuto",tr);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
