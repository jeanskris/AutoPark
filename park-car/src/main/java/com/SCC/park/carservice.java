package com.SCC.park;

import com.SCC.park.mina.TcpClient;
import org.apache.mina.core.buffer.IoBuffer;
import org.springframework.stereotype.Service;

/**
 * Created by ZJDX on 2016/6/20.
 */
@Service
public class carservice {
    public void sendPath2Car(){
        TcpClient tcpClient=new TcpClient();
        byte[] bytes = { 0x11};
        tcpClient.send(IoBuffer.wrap(bytes));
    }
}
