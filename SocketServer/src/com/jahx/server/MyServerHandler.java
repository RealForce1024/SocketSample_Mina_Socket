package com.jahx.server;

import com.jahx.jsonutil.GPRSEntityToJsonUtil;
import com.jahx.jsonutil.JsonToGPRSEntityUtil;
import com.jahx.persistence.DBUtils;
import com.jahx.protocol.DateEntity;
import com.jahx.protocol.DateUtil;
import com.jahx.protocol.GPRSEntity;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import java.net.SocketAddress;
import java.util.Map;

/**
 * event handler
 */
public class MyServerHandler extends IoHandlerAdapter {

    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        System.out.println("exceptionCaught");
        System.out.println(cause.getStackTrace());
        cause.printStackTrace(); //堆栈异常信息打印
    }

    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {

        //messageReceivedStrHandler(session, (String) message);

        //接收字符串
       /* String s = (String) message;
        System.out.println("received msg:=======" + s);*/

        //接收字节数组
        //message 是字符串类型
        /*System.out.println("class:"+message.getClass());
        String message1 = (String) message;
        System.out.println("message1=="+message1);
        byte[] bytes = message1.getBytes();
        for (Byte aByte : bytes) {
            System.out.print(aByte);
        }*/


        //使用session对象获取
        /*SocketAddress remoteAddress = session.getRemoteAddress();
        System.out.println(remoteAddress);

        long readBytes = session.getReadMessages();
        System.out.println(readBytes);*/
        /*if (message instanceof IoBuffer) {
            IoBuffer ioBuffer = (IoBuffer) message;
            int bufferSize = ioBuffer.limit();
            if (bufferSize>3) {
                byte[] byteBuff = new byte[bufferSize];
                System.arraycopy(ioBuffer, 0, byteBuff, 0, bufferSize);
                if (byteBuff[2]==0x82) { //血糖数据
                    //首先要确定 ioBuffer的内容
                    GPRSEntity gprsEntity = new GPRSEntity(ioBuffer);

                }

            }
        }*/


    }

    private void messageReceivedStrHandler(IoSession session, String message) throws java.io.IOException {
        String s = message;

        System.out.println("messageReceived: " + s);

        //业务处理 start
        //将json转为实体
        GPRSEntity gprsEntity = new JsonToGPRSEntityUtil().JsonToGPRSEntity(s);
        //将存储

        new DBUtils().save_gprs(gprsEntity);

        //根据请求头 是否响应时间。
        String id = gprsEntity.getHead().getId();
        String timeJson = null;
//         char id; //ID=0x82(表示血糖数据)  ID=0x90（表示请求时间同步）
        if ("0x90".equals(id)) {
            //返回时间
            Map mapTime = DateUtil.getTimeByCalendar();
            DateEntity tm = new DateEntity(mapTime);
            timeJson = new GPRSEntityToJsonUtil().EntityToJson(tm);
//            session.write("server reply: " + timeJson.getBytes());

        }


//        if (null != timeJson) {
//            session.write(timeJson);
//        }
//        session.write("server reply: "+timeJson);
        //收到数据包 响应RGH
        //业务处理end
//        session.write("server receive success: RGH");
        session.write("server reply: " + s);
//        System.out.println(s.getBytes());
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        System.out.println("messageSent");
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("sessionClosed");
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("sessionCreated");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status)
            throws Exception {
        System.out.println("sessionIdle");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("sessionOpened");
    }


    public static void main(String[] args) throws Exception {
        Map mapTime = DateUtil.getTimeByCalendar();
        DateEntity tm = new DateEntity(mapTime);
        new GPRSEntityToJsonUtil().EntityToJson(tm);
    }
}