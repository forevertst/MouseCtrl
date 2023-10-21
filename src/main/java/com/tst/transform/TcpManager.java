package com.tst.transform;

public class TcpManager {
    static TcpServer tcpServerRunnable;

    public static void startTcpServer() {
        tcpServerRunnable = new TcpServer(5000);
        Thread thread = new Thread(tcpServerRunnable);
        thread.start();
    }
    public static void stopTcpClient() {
        tcpServerRunnable.cancel();
    }
}
