package com.zz.tst.aop;

public class MonitorSession {

    private static ThreadLocal<MethodMonitor> monitorThreadLocal = new ThreadLocal<>();

    public static void begin(String method) {
        MethodMonitor methodMonitor = new MethodMonitor(method);
        monitorThreadLocal.set(methodMonitor);
    }

    public static void end() {
        MethodMonitor methodMonitor = monitorThreadLocal.get();
        monitorThreadLocal.set(methodMonitor);
        methodMonitor.log();
    }
}
