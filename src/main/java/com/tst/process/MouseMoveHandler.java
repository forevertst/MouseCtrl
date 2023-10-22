package com.tst.process;

import com.tst.pojo.Point;
import com.tst.utils.Mouse;

import static com.tst.global.Data.movePointList;

public class MouseMoveHandler implements Runnable {
    public Point last;
    boolean stopFlag = false;
    public void resetMousePos() {
        this.last = null;
    }
    public void stop() {
        stopFlag =true;
    }
    @Override
    public void run() {
        while (true) {
            if(stopFlag)
            {
                break;
            }
            if (!movePointList.isEmpty()) {
                Point point = movePointList.get(0);
                if (last == null) {
                    last = point;
                } else {
                    int xOffset = point.getX() - last.getX();
                    int yOffset = point.getY() - last.getY();
                    Mouse.move(xOffset, yOffset);
                    last = point;
                }
                movePointList.remove(0);
            }
        }
    }
}
