package com.jahx.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MinaClient {

	public static void main(String[] args) throws Exception{
		NioSocketConnector connector = new NioSocketConnector();
		connector.setHandler(new MyClientHandler());
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory()));
		ConnectFuture future = connector.connect(new InetSocketAddress("127.0.0.1", 9898));
		future.awaitUninterruptibly();
		IoSession session = future.getSession();
//		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
//		String inputContent;
//		while (!(inputContent   = inputReader.readLine()).equals("bye")) {
//			session.write(inputContent);
//		}
        String json = "{\"uuid\":\"1\",\"flag\":\"0\",\"glu\":0.1,\"head\":{\"id\":\"0x90\",\"flag2\":\"0\",\"flag1\":\"0\"},\"tm\":{\"year\":2015,\"date\":11,\"mon\":7,\"hr\":2,\"min\":25,\"sec\":16}}";
        String json2 = "{\"uuid\":\"1\",\"flag\":\"0\",\"glu\":0.1,\"head\":{\"id\":\"0xAA\",\"flag2\":\"0\",\"flag1\":\"0\"}}";
//        session.write(json2);
//        session.write(json.getBytes());
        session.write("bytes:"+json);
        System.out.println("bytes.length"+json.getBytes().length);

    }

}
