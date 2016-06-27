import com.SCC.park.mina.TcpClient;
import com.SCC.park.utils.Constant;
import org.apache.mina.core.buffer.IoBuffer;

import java.net.SocketAddress;

/**
 * Created by ZJDX on 2016/6/23.
 */
public class TCPClientTest {
    public static void main(String[] args) {//test reading from db
        TcpClient tcpClient=new TcpClient();
        byte[] bytes = Constant.FORWARD;
        tcpClient.send(IoBuffer.wrap(bytes));
        SocketAddress address=tcpClient.getAddress();
        System.out.println("Constant.TCP_REMOTE_SERVER_IP:" +Constant.TCP_REMOTE_SERVER_IP);
        System.out.println("address.toString() " +address.toString());


    }
}
