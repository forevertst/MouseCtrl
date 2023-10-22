package com.tst;

import com.tst.process.ProcessManager;
import com.tst.transform.TcpManager;
import com.tst.transform.UdpManager;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        TcpManager.startTcpServer();
        UdpManager.startUdpServer();
        UdpManager.startUdpClient();

        ProcessManager.startMouseMoveHandlerThread();

        Runnable shutdownHook = new Runnable() {
            public void run() {
                System.out.println("程序即将终止，执行清理工作...");
                // 在这里执行程序终止时要执行的操作
                TcpManager.stopTcpClient();
                ProcessManager.stopMouseMoveHandlerThread();

                UdpManager.stopUdpServer();
                UdpManager.stopUdpClient();
            }
        };

        // 注册 Shutdown Hook
        Runtime.getRuntime().addShutdownHook(new Thread(shutdownHook));
    }
}
