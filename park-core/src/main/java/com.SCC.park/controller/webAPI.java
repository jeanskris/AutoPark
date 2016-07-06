package com.SCC.park.controller;

import com.SCC.park.CarService;
import com.SCC.park.LocateService;
import com.SCC.park.mina.TCPServerHandler;
import com.SCC.park.model.PointInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
/**
 * Created by ZJDX on 2016/6/20.
 */
@Controller
public class webAPI {
    private static Logger logger = LoggerFactory.getLogger(webAPI.class);

    @Autowired
    LocateService locateService;
    @Autowired
    CarService carService;
    @Autowired
    TCPServerHandler tcpServerHandler;
    @RequestMapping(value = "/park")
    public void park(@RequestParam(value="carId", required=false) Integer id){
        PointInfo pointInfo=locateService.getPoint(id);
    }

    /**
     * index1
     * when client request"/",server return index.html
     * @return no
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index1(){
        System.out.println("/");
        return new ModelAndView("hello");
    }
    /**
     * index
     * when client request"/index.html",server return index.html
     * @return no
     */
    @CrossOrigin
    @RequestMapping(value = "/index.html")
    public ModelAndView index(){
        System.out.println("/index");
        ModelAndView mv = new ModelAndView("index");

        return mv;
    }


}
