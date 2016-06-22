package com.SCC.park;

import com.SCC.park.model.PointInfo;
import com.SCC.park.monitor.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ZJDX on 2016/6/20.
 */
@Service("locateService")
public class LocateService {
    @Autowired
    MonitorService monitorService;

    public PointInfo getPoint(int carID){
        PointInfo pointInfo =monitorService.getPointInfo(carID);
        return pointInfo;

    }

}
