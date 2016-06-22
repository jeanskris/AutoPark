import com.SCC.park.LocateService;
import com.SCC.park.model.PointInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:dispatcher-servlet.xml"})
public class test {
    private static Logger log = LoggerFactory.getLogger(test.class);

    @Autowired
    LocateService locateService;

    @Test
    public void getP(){
        try {
            PointInfo pointInfo=locateService.getPoint(1);
            System.out.println(pointInfo.getAngle());
        } catch(Exception e) {
            log.info(e.getMessage());
        }
    }

}
