package com.SCC.park;


import com.SCC.park.model.Coordinate;
import com.SCC.park.model.Path;
import org.springframework.stereotype.Service;

/**
 * Created by ZJDX on 2016/6/20.
 */
@Service("pathService")
public class PathService {
    public Path getPath(Coordinate start,Coordinate end){
        Path path = new Path();
        path.setStartPoint(start);
        path.setEndPoint(end);
        return  path;
    }
}
