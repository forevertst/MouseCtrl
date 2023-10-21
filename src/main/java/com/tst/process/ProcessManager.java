package com.tst.process;

public class ProcessManager {
    static MouseMoveThread mouseMoveThread;

    public static void resetMousePos() {
        mouseMoveThread.last = null;
    }

    public static void startMouseMoveThread() {
        mouseMoveThread = new MouseMoveThread();
        Thread thread = new Thread(mouseMoveThread);
        thread.start();
    }
}
