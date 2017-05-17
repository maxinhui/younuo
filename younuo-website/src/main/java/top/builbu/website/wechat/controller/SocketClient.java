package top.builbu.website.wechat.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
    Socket socket = null;
     
    public static void main(String[] args) throws IOException {
    	OutputStreamWriter outSW = null; 
//        System.out.println(">>>>>> : 服务端 " + i + " 开始！" + System.currentTimeMillis());
        //客户端
        //1、创建客户端Socket，指定服务器地址和端口
            Socket socket = new Socket("127.0.0.1", 8888);
//        Socket socket = new Socket("106.14.22.17", 10086);
        //2、获取输出流，向服务器端发送信息
            outSW = new OutputStreamWriter(socket.getOutputStream(),"UTF-8");
            BufferedWriter bw = new BufferedWriter(outSW);
//            pw.write("685951B02BC9181E2BC9181E8101053030303530343934303834332D30303100A0000500000000000000000000000000000000088E000000001388000000000000000E000A0000000108E20000000000020000BE34040400010000000500000000000000011316");
//        pw.write("10615450649740756204046208064240488202082048004808604026846200042602088422220600200640262480684220244466060884402444462026806024224086486422648202044842002886864220666668848888682644248682008808226062824020206826460048608060264642042080888");
        bw.write("java 套子节");
        bw.flush();
            outSW.close();
            bw.close();
            socket.close();
//        System.out.println(">>>>>> : 服务端 " + i + " 结束！" + System.currentTimeMillis());
//        }
    }

}