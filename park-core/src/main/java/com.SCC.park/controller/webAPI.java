package com.SCC.park.controller;

import com.SCC.park.LocateService;
import com.SCC.park.model.PointInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by ZJDX on 2016/6/20.
 */
@Controller
public class webAPI {
    @Autowired
    LocateService locateService;
    @RequestMapping(value = "/park")
    public void park(@RequestParam(value="carId", required=false) Integer id){
        PointInfo pointInfo=locateService.getPoint(id);
    }


}
