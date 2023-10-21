package com.tst.transform;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer {
    static DatagramSocket socket;
    static boolean stopFlag = false;

    public static void stop() {
        stopFlag = true;
    }

    public static void init() {
        try {
            stopFlag = false;
            // 创建 DatagramSocket 绑定到指定端口
            int port = 5001;
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
                if (receivedMessage.equals("1;")) {
                    UdpClient.sendUdpRequest();
                    UdpServer.stop();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
