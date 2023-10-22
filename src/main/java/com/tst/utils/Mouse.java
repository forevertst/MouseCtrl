package com.tst.utils;

import java.awt.*;
import java.awt.Point;
import java.awt.event.InputEvent;

public class Mouse {
    static Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public static void move(int xOffset, int yOffset) {
        try {
            // 获取当前鼠标位置
            Point currentMouseLocation = MouseInfo.getPointerInfo().getLocation();
            int currentX = (int) currentMouseLocation.getX();
            int currentY = (int) currentMouseLocation.getY();

            int steps = 50;  // 步数

            for (int i = 0; i <= steps; i++) {
                double progress = (double) i / steps;
                int x = (int) (currentX + xOffset * progress);
                int y = (int) (currentY + yOffset * progress);
                robot.mouseMove(x, y);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void leftClick() {
        try {
            Robot robot = new Robot();
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // 模拟鼠标左键按下
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // 模拟鼠标左键释放

        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public static void RightClick() {
        Robot robot = null;
        try {
            robot = new Robot();
            robot.mousePress(InputEvent.BUTTON3_DOWN_MASK); // 模拟鼠标右键按下
            robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK); // 模拟鼠标右键释放
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }
}
