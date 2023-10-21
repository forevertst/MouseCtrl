package com.tst.process;

public class ProcessManager {
    static MouseMoveThread mouseMoveThread;
    public static void startMouseMoveThread() {
        mouseMoveThread = new MouseMoveThread();
        Thread thread = new Thread(mouseMoveThread);
        thread.start();
    }
}
