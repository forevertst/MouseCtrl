package com.tst.utils;

import java.awt.*;
import java.awt.Point;
import java.awt.event.InputEvent;

public class Mouse {
    public static void move(int xOffset, int yOffset) {
        try {
            Robot robot = new Robot();
            // 获取当前鼠标位置
            Point currentMouseLocation = MouseInfo.getPointerInfo().getLocation();
            int x = (int) currentMouseLocation.getX();
            int y = (int) currentMouseLocation.getY();
            x = x + xOffset;
            y = y + yOffset;
            robot.mouseMove(x, y);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public static void click() {
        try {
            Robot robot = new Robot();
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // 模拟鼠标左键按下
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // 模拟鼠标左键释放
//            robot.mousePress(InputEvent.BUTTON3_DOWN_MASK); // 模拟鼠标右键按下
//            robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK); // 模拟鼠标右键释放
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }
}
