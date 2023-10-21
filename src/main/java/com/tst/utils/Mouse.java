package com.tst.utils;

import java.awt.*;
import java.awt.Point;

public class Mouse {
    public static void move(int xOffset, int yOffset) {
        try {
            Robot robot = new Robot();
            // 获取当前鼠标位置
            Point currentMouseLocation = MouseInfo.getPointerInfo().getLocation();
            int x = (int) currentMouseLocation.getX();
            int y = (int) currentMouseLocation.getY();
            x = x - xOffset;
            y = y + yOffset;
            robot.mouseMove(x, y);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }
}
