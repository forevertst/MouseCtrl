package com.tst.transform;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

public class UdpClient {
    DatagramSocket socket;
    String message;

    int broadcastPort;

    UdpClient(int broadcastPort, String netAddressName, String message) {
        this.broadcastPort = broadcastPort;
        this.message = message;
        try {
            // 创建一个 DatagramSocket 对象，不需要指定目标主机和端口
            socket = new DatagramSocket(5002, InetAddress.getByName(netAddressName));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void sendUdpRequest() {
        try {
            // 指定广播地址（通常是 255.255.255.255）
            InetAddress broadcastAddress = InetAddress.getByName("255.255.255.255");

            // 发送的消息内容
            String message = this.message;

            // 将消息转换为字节数组
            byte[] messageBytes = message.getBytes();

            // 创建 DatagramPacket 对象，包含消息内容和目标地址
            DatagramPacket packet = new DatagramPacket(messageBytes, messageBytes.length, broadcastAddress, broadcastPort);
            // 发送广播消息
            if (!TcpManager.isConnect()) {
                socket.send(packet);
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void cancel() {
        // 关闭 Socket
        socket.close();
    }
}
