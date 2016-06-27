package com.SCC.park.controller;

import com.SCC.park.CarService;
import com.SCC.park.LocateService;
import com.SCC.park.mina.TCPServerHandler;
import com.SCC.park.model.ResponseMessage;
import com.SCC.park.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping(value = "/startAutoPark", method = RequestMethod.GET)
    public void startAutoPark(){
        logger.debug("run");
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
        carService.sendCommand(Constant.FOLLOW_MEGNETIC);
    }
}
