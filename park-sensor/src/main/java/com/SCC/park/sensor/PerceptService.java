package com.SCC.park.sensor;

import com.SCC.park.model.Coordinate;
import com.SCC.park.model.PointInfo;
import org.springframework.stereotype.Service;

/**
 * Created by ZJDX on 2016/6/20.
 */
@Service("perceptService")
public class PerceptService {
    public PointInfo getPointInfoFromSensor(int carID){
        PointInfo pointInfo=new PointInfo();
        pointInfo.setCoordinate(new Coordinate(5.5,5.5));
        pointInfo.setAngle(80);
        pointInfo.setSpeed(10);
        return pointInfo;
    }

}
