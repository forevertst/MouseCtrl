package com.tst.transform;

import static java.lang.Thread.sleep;


public class UdpManager {
    static boolean udpClientSendFlag = false;

    public static void startUdpServer() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                UdpServer.init();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public static void stopUdpServer() {
        UdpServer.stop();
    }
    public static void stopUdpClient() {
        UdpClient.cancel();
    }
}
