package com.tst.transform;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer {
    DatagramSocket socket;
    boolean stopFlag = false;
    int port;
    String listenMessage;

    UdpServer(int port, String listenMessage) {
        this.port = port;
        this.listenMessage = listenMessage;
    }

    public void stop() {
        stopFlag = true;
    }

    public void init() {
        try {
            stopFlag = false;
            // 创建 DatagramSocket 绑定到指定端口
            socket = new DatagramSocket(port);

            System.out.println("UDP Server is running on port " + port);

            while (true) {
                if (stopFlag) {
                    socket.close();
                    break;
                }
                // 创建用于接收数据的 DatagramPacket
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // 接收数据包
                socket.receive(receivePacket);

                // 解析接收到的数据
                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                String senderAddress = receivePacket.getAddress().getHostAddress();
                int senderPort = receivePacket.getPort();

                System.out.println("Received from " + senderAddress + ":" + senderPort + " - " + receivedMessage);

                if (receivedMessage.equals(listenMessage)) {
                    UdpManager.udpClientSend();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
