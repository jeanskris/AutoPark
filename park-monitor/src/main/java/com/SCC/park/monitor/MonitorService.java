package com.SCC.park.monitor;

import com.SCC.park.model.PointInfo;
import com.SCC.park.sensor.PerceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ZJDX on 2016/6/20.
 */
@Service("monitorService")
public class MonitorService {
    @Autowired
    PerceptService perceptService;

    public PointInfo getPointInfo(int carID){
        PointInfo pointInfo=perceptService.getPointInfoFromSensor(carID);
        return pointInfo;
    }
}
