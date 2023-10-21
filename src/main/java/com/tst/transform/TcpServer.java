package com.tst.transform;

import com.tst.pojo.Point;
import com.tst.process.ProcessManager;
import com.tst.utils.Mouse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Objects;

import static com.tst.common.Data.movePointList;
import static java.lang.Thread.sleep;

public class TcpServer implements Runnable {
    int port;
    public boolean isConntct = false;

    TcpServer(int p) {
        port = p;
    }

    public ServerSocket serverSocket;

    void handleClient(Socket clientSocket) {
        isConntct = true;
        try {
            InputStream inputStream;
            inputStream = clientSocket.getInputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;


            while ((bytesRead = inputStream.read(buffer)) != -1) {
                System.out.println(String.valueOf(bytesRead));
                String message = new String(buffer, 0, bytesRead);
                System.out.println("Received from client: " + message);

                // 在这里可以添加处理接收到的数据的逻辑
                List<String> list = List.of(message.split(";"));
                list.forEach(position -> {
                    List<String> list2 = List.of(position.split(","));
                    if (list2.size() > 0) {
                        if (Objects.equals(list2.get(0), "mouseMove")) {
                            if (list2.size() > 2) {
                                Point newP = new Point((int) Float.parseFloat(list2.get(1)), (int) Float.parseFloat(list2.get(2)));
                                movePointList.add(newP);
                            }
                        } else if (Objects.equals(list2.get(0), "mouseClick")) {
                            Mouse.click();
                        } else if (Objects.equals(list2.get(0), "mousePosInit")) {
                            ProcessManager.resetMousePos();
                        }
                    }
                });
            }
            // 客户端断开连接
            System.out.println("客户端断开连接：" + clientSocket.getInetAddress());
            // 关闭连接
            clientSocket.close();
            isConntct = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancel() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("TCP server is listening on port " + port);

            while (true) {
                // 等待客户端连接
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // 处理客户端连接
                Thread clientThread = new Thread(() -> handleClient(clientSocket));
                clientThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
