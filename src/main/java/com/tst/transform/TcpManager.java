package com.tst.transform;

public class TcpManager {
    static TcpServer tcpServer;

    public static boolean isConnect() {
        return tcpServer.isConntct();
    }

    public static void startTcpServer() {
        tcpServer = new TcpServer(5000);
        Thread thread = new Thread(tcpServer);
        thread.start();
    }

    public static void stopTcpClient() {
        tcpServer.cancel();
    }
}
