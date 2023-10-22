package com.tst.global;

import com.sun.tools.javac.Main;
import com.tst.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class UI extends JFrame {
    public UI() {
        init();
    }

    public void init() {
        this.setTray();
        this.setVisible(false);
    }

    //添加托盘显示：1.先判断当前平台是否支持托盘显示
    public void setTray() {

        if (SystemTray.isSupported()) {//判断当前平台是否支持托盘功能
            //创建托盘实例
            SystemTray tray = SystemTray.getSystemTray();
            //创建托盘图标：1.显示图标Image 2.停留提示text 3.弹出菜单popupMenu 4.创建托盘图标实例

            //1.创建Image图像
            // 获取当前类的 ClassLoader
            ClassLoader classLoader = Main.class.getClassLoader();
            // 使用相对路径加载图标（假设图标位于JAR文件的根目录下）
            String relativePath = "image/icons8-double-bed-16.png";
            // 通过 ClassLoader 获取资源的 URL
            URL imageURL = classLoader.getResource(relativePath);
            ImageIcon icon = new ImageIcon(imageURL);
            Image image = icon.getImage();
//            Image image = Toolkit.getDefaultToolkit().getImage(path);

            //2.停留提示text
            String text = "手机_WIFI_电脑_鼠标控制";
            //3.弹出菜单popupMenu
            PopupMenu popMenu = new PopupMenu();
            MenuItem itmExit = new MenuItem("exit");
            itmExit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Exit();
                }
            });

            popMenu.add(itmExit);

            //创建托盘图标
            TrayIcon trayIcon = new TrayIcon(image, text, popMenu);
            //将托盘图标加到托盘上
            try {
                tray.add(trayIcon);
            } catch (AWTException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void Exit() {
        System.exit(0);
    }
}
