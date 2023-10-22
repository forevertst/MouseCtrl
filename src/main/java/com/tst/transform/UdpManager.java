package com.tst.transform;

import com.tst.global.Data;

import static java.lang.Thread.sleep;


public class UdpManager {
    static UdpClient udpClient;
    static UdpServer udpServer;

    public static void startUdpServer() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                udpServer = new UdpServer(5001, "1;");
                udpServer.init();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public static void stopUdpServer() {
        udpServer.stop();
    }

    public static void startUdpClient() {
        String ip = Data.ipAddress;
        udpClient = new UdpClient(5002, ip, "2;");
    }

    public static void udpClientSend() {
        udpClient.sendUdpRequest();
    }

    public static void stopUdpClient() {
        udpClient.cancel();
    }
}
