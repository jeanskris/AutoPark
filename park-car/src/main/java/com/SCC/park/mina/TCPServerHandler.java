package com.SCC.park.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Component;

@Component("tcpServerHandler")
public class TCPServerHandler extends IoHandlerAdapter {
/*
    @Autowired
    ICarInfoService carInfoService;*/
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
        super.exceptionCaught(session, cause);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        System.out.println("data from client:"+message);
        if (message instanceof IoBuffer) {
            IoBuffer buffer = (IoBuffer) message;
            System.out.println(buffer.toString());
            buffer.setAutoShrink(true);
            buffer.setAutoExpand(true);
            byte[] bytes = new byte[buffer.limit()];
            buffer.get(bytes);

            if(bytes[2]==0x30) {
                /*carInfoService.updatePower(bytes);//调用service业务处理*/
            }
        }
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
        System.out.println("data has been sent to client");
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
        System.out.println("Server created a session: " + session.getRemoteAddress());
    }
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("Server sessionClosed" );
    }

    @Override
    public void sessionIdle(IoSession iosession, IdleStatus idlestatus)
            throws Exception {
        System.out.println("sessionIdle");
        super.sessionIdle(iosession, idlestatus);
    }
    @Override
    public void sessionOpened(IoSession iosession) throws Exception {
        System.out.println("sessionOpened");
        super.sessionOpened(iosession);
    }
}
