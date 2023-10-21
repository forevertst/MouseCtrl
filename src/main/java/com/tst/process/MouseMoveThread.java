package com.tst.process;

import com.tst.pojo.Point;
import com.tst.utils.Mouse;

import static com.tst.common.Data.movePointList;

public class MouseMoveThread implements Runnable {
    Point last;

    @Override
    public void run() {
        while (true) {
            if (!movePointList.isEmpty()) {
                System.out.println("in isNotEmpty");
                Point point = movePointList.get(0);
                if (last == null) {
                    last = point;
                } else {
                    int xOffset = point.getX() - last.getX();
                    int yOffset = point.getY() - last.getY();
                    Mouse.move(xOffset, yOffset);
                    System.out.println("MouseMove: " + xOffset + "," + yOffset);
                    last = point;
                }
                movePointList.remove(0);
            }
        }

    }
}