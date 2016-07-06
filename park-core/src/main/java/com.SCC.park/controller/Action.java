package com.SCC.park.controller;

import com.SCC.park.CarService;
import com.SCC.park.LocateService;
import com.SCC.park.mina.TCPServerHandler;
import com.SCC.park.model.Coordinate;
import com.SCC.park.model.HttpRequest;
import com.SCC.park.model.ResponseMessage;
import com.SCC.park.sensor.Map;
import com.SCC.park.sensor.PerceptService;
import com.SCC.park.utils.Constant;
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
        HttpRequest hr=new HttpRequest();
        String result=hr.sendGet("http://localhost:8081/startByPlatform","");
        System.out.println(result);
    }


    @RequestMapping(value = "/getDataFromPlatform", method = RequestMethod.GET)
    public String getDataFromPlatform(){
        logger.debug("getDataFromPlatform");
        HttpRequest hr=new HttpRequest();
        String result=hr.sendGet("http://localhost:8081/getData","");
        System.out.println(result);
        return result;
    }
    @RequestMapping(value = "/getMap", method = RequestMethod.GET)
    public Map getMap(@RequestParam(value="mapId", required=false) Integer id) {
        System.out.println("mapId:" + id);
        Map map=perceptService.getMapByOpencv();
        return map;
    }

    @RequestMapping(value = "/mouseClickPoint", method = RequestMethod.POST)
    public void mouseClickPoint(@RequestBody Coordinate coordinate) {
        System.out.println("coordinate:" + coordinate.getX());
    }
}
