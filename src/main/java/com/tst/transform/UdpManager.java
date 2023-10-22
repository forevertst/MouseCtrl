package com.tst.transform;

import static java.lang.Thread.sleep;


public class UdpManager {
    static UdpClient udpClient;
    static UdpServer udpServer;

    public static void startUdpServer() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                udpServer = new UdpServer(5001,"1;");
                udpServer.init();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public static void stopUdpServer() {
        udpServer.stop();
    }

    public static void startUdpClient(){
        udpClient = new UdpClient(5002,"192.168.2.11","2;");
    }
    public static void udpClientSend(){
        udpClient.sendUdpRequest();
    }
    public static void stopUdpClient() {
        udpClient.cancel();
    }
}
