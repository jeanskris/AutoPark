import com.SCC.park.utils.Constant;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * Created by ZJDX on 2016/5/20.
 */

public class TestTCP {

//    private static String HOST = "114.215.144.107 ";
    private static String HOST = "10.214.143.78 ";
    private static int PORT = 5555;
    static public class MinaClientHandler extends IoHandlerAdapter {
        @Override
        public void sessionCreated(IoSession session) throws Exception {
//            super.sessionCreated(session);
            System.out.println("Session created");
        }

        @Override
        public void messageReceived(IoSession session, Object message) throws Exception {
            super.messageReceived(session, message);
            System.out.println("message Received" + message.toString());
        }

        @Override
        public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
            super.exceptionCaught(session, cause);
        }

    }

    public static void main(String[] args) {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            System.out.println(addr.getHostAddress());
        }
        catch (Exception e){
            System.out.println(e.getMessage().toString());
        }

        IoConnector conn = new NioSocketConnector();
        conn.setConnectTimeoutMillis(3000l);

        conn.setHandler(new MinaClientHandler());
        IoSession session = null;

            try {
                ConnectFuture future = conn.connect(new InetSocketAddress(HOST, PORT));
                future.awaitUninterruptibly();
                session = future.getSession();

                byte[] GpsLattude=float2byte((float)0.01);//4 bytes
                byte[] GpsLongitude=float2byte((float)0.01);
                byte[] bytes = Constant.FORWARD;
                int times=0;
                while (true){
                    times++;
                    session.write(IoBuffer.wrap(bytes));
                    System.out.println("times sended:"+times);
                    Thread.sleep(10000);
                }

            } catch (Exception e) {
                System.out.println("Exception:" + e.toString() + "\n details:" + e.getCause().toString());
            }

        session.close();
        session.getCloseFuture().awaitUninterruptibly();
        conn.dispose();
    }
    /**
     * float2byte
     *
     * @param f float data
     * @return byte[]
     */
    public static byte[] float2byte(float f) {

        // floatToIntBits
        int fbit = Float.floatToIntBits(f);

        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (fbit >> (24 - i * 8));
        }

        // inverse
        int len = b.length;
        // create an array  same as original data
        byte[] dest = new byte[len];
        // copy array as a reduplication
        System.arraycopy(b, 0, dest, 0, len);
        byte temp;
        // exchange first-to-last ith and last-to-first ith
        for (int i = 0; i < len / 2; ++i) {
            temp = dest[i];
            dest[i] = dest[len - i - 1];
            dest[len - i - 1] = temp;
        }

        return dest;

    }
}
