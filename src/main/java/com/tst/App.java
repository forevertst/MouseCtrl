package com.tst;

import com.tst.global.Data;
import com.tst.global.UI;
import com.tst.process.ProcessManager;
import com.tst.transform.TcpManager;
import com.tst.transform.UdpManager;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        // 获取命令行参数
        if (args.length > 0) {
            Data.ipAddress = args[0];
        }
        for (int i = 0; i < args.length; i++) {
            System.out.println("参数 " + (i + 1) + ": " + args[i]);
        }
        new UI();
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
