package top.builbu.website.wechat.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class SocketServer {

    ServerSocket ss = null;

    public SocketServer(int port) throws IOException {
        ss = new ServerSocket(port);
    }

    public void listen() {
        Socket socket = null;
        while (true) {
            try {
                socket = ss.accept();
                new Thread(new ServerImpl(socket)).start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class ServerImpl implements Runnable {
        Socket client = null;

        public ServerImpl(Socket client) {
            this.client = client;
        }

        public synchronized void run() {
           
            
            	BufferedReader b = null;
    		    String r = "";
    		    int i = 0;
    		    while(!client.isClosed()){
    		    	 try {

	    		    	b = new BufferedReader(new InputStreamReader(client.getInputStream()));
	    		    	r = b.readLine();
	    		    	i++;
	    		    	System.out.print(r+i);
    		    	 } catch (IOException e) {
    		                e.printStackTrace();
    		            } finally {

    		                if (client != null) {
    		                	try {
										client.close();
									} catch (IOException e) {										
										e.printStackTrace();
									}

    		                   
    		                }
    		            }
    		    }
              

            

        }

    }


    public static void main(String[] args) throws IOException {

        SocketServer server = new SocketServer(8888);

        server.listen();
    }

}