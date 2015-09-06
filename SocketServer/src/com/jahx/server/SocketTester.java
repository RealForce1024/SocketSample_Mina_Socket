package com.jahx.server;

import javax.sound.midi.Soundbank;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTester {

    public static void main(String[] args){
		try {
			ServerSocket serverSocket = new ServerSocket(9898);
            System.out.println("server started...");
            while (true){
				Socket socket=serverSocket.accept();
				socket.setSoTimeout(30000);
				
				BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
				byte[] b = new byte[1024];
				int i = in.read(b);
				String str = new String(b, 0, i);
				System.out.println("RECEIVE MESSAGE："+str);
				
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				System.out.println("SEND MESSAGE: 101010");
				out.write("101010");
				out.flush();
				
				in.close();
				out.close();
				socket.close();
		    }
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}