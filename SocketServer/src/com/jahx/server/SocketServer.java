package com.jahx.server;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	public static void main(String[] args) {
		SocketServer socketServer = new SocketServer();
		socketServer.startServer();
	}
	
	public void startServer() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(9898);
			System.out.println("server started..");
			while (true) {
				socket = serverSocket.accept();
				manageConnection2(socket);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void manageConnection(final Socket socket) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				BufferedReader reader = null;
				BufferedWriter writer =null;
				try {
					System.out.println("client " + socket.hashCode() + " connedted");
					reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
					String receivedMsg;
					while ((receivedMsg = reader.readLine()) != null) {
						System.out.println("client " + socket.hashCode() + ": " + receivedMsg);
						writer.write("server reply: " + receivedMsg + "\n");
						writer.flush();
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						reader.close();
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

    public void manageConnection2(final Socket socket) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader reader = null;
                BufferedWriter writer =null;
                BufferedInputStream in = null;
                BufferedWriter out = null;
                try {
                    /*System.out.println("client " + socket.hashCode() + " connedted");
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    String receivedMsg;
                    while ((receivedMsg = reader.readLine()) != null) {
                        System.out.println("client " + socket.hashCode() + ": " + receivedMsg);
                        writer.write("server reply: " + receivedMsg + "\n");
                        writer.flush();
                    }*/

                    in = new BufferedInputStream(socket.getInputStream());
                    out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    byte[] b = new byte[256];
                    int i = 0;

                    while ((i = in.read(b))!=0) {
                        System.out.println("==b:"+b);
                        for (byte b1:b){
                            System.out.print(b1);
                            System.out.println((char)b1);
                        }
                        System.out.println();
                        String str = new String(b, 0, i);
                        System.out.println("client " + socket.hashCode() + ": " + str);

                        System.out.println("SEND MESSAGE: 101010");
                        out.write("101010 \n");
                        out.flush();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {

                        in.close();
                        out.close();
//                        socket.close();
//                        reader.close();
//                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


}