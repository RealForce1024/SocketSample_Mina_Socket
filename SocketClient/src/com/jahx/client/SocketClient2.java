package com.jahx.client;

/**
 * Created by fqc on 15/9/1.
 */
    import java.io.*;
    import java.net.*;

    public class SocketClient2
    {
        void query(String file,String ip,int port)
        {
            FileInputStream fileInputStream;
            DataInputStream netInputStream;
            DataOutputStream netOutputStream;
            Socket sc;
            int fileLength;
            byte[] buffer=new byte[1023];
            byte[] readLen=new byte[10];
            byte[] readResult=new byte[2000];
            int len;
            int result_count=0;

            File f=new File(file);
            if(f.exists())
            {
                fileLength=(int)f.length();
            }
            else
            {
                System.out.println("No such file");
                return;
            }

            try
            {
                fileInputStream=new FileInputStream(file);
                sc=new Socket(ip,port);
                netInputStream=new DataInputStream(sc.getInputStream());
                netOutputStream=new DataOutputStream(sc.getOutputStream());

                /////////////////////1.send file length//////////////////////
                netOutputStream.write(Integer.toString(fileLength).getBytes());

                /////////////////////2. send file///////////////////////////
                while((len=fileInputStream.read(buffer))>0)
                {
                    netOutputStream.write(buffer,0,len);
                }

                ////////////////3. read result symbol///////////////////////////////
                netInputStream.read(readLen);

                while(((char)readLen[0])=='1')
                {
                    /////////////////////4. Read result//////////////////////////////
                    netInputStream.read(readResult);
                    String result=new String(readResult);
                    String[] ss=result.split(",");

                    int score=Integer.parseInt(ss[3]);
                    int startTime=Integer.parseInt(ss[4]);
                    double confidence=Double.parseDouble(ss[5]);

                    System.out.println("name:"+ss[0].trim());
                    System.out.println("artist:"+ss[1].trim());
                    System.out.println("album:"+ss[2].trim());
                    System.out.println("score:"+score);
                    System.out.println("startTime:"+startTime);
                    System.out.println("confidence:"+confidence);

                    result_count++;

                    netInputStream.read(readLen);
                }

                if(result_count==0)
                {
                    System.out.println("No match music");
                }

                fileInputStream.close();
                netInputStream.close();
                netOutputStream.close();
                sc.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        public static void main(String[] args)
        {
            SocketClient2 client=new SocketClient2();
            client.query(args[0],args[1],9527);
        }
    }

