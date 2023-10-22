package com.tst.process;

public class ProcessManager {
    static MouseMoveHandler mouseMoveHandler;

    public static void resetMousePos() {
        mouseMoveHandler.resetMousePos();
    }

    public static void startMouseMoveHandlerThread() {
        mouseMoveHandler = new MouseMoveHandler();
        Thread thread = new Thread(mouseMoveHandler);
        thread.start();
    }

    public static void stopMouseMoveHandlerThread() {
        mouseMoveHandler.stop();
    }
}
