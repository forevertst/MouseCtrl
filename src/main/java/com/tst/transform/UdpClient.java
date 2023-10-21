package com.tst.transform;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

public class UdpClient {
    static DatagramSocket socket;
    static String message = "2;";

    static {
        try {
            // 创建一个 DatagramSocket 对象，不需要指定目标主机和端口
            socket = new DatagramSocket(5002, InetAddress.getByName("192.168.2.11"));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendUdpRequest() {
        try {
            // 指定广播地址（通常是 255.255.255.255）
            InetAddress broadcastAddress = InetAddress.getByName("255.255.255.255");

            // 发送的消息内容
            String message = UdpClient.message;

            // 将消息转换为字节数组
            byte[] messageBytes = message.getBytes();

            // 创建 DatagramPacket 对象，包含消息内容和目标地址
            DatagramPacket packet = new DatagramPacket(messageBytes, messageBytes.length, broadcastAddress, 5002);
            // 发送广播消息
            if(!TcpManager.isConnect()){
                socket.send(packet);
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public static void cancel() {
        // 关闭 Socket
        socket.close();
    }
}
