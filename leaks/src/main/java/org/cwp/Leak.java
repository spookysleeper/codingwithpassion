package org.cwp;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryType;
import java.lang.management.MemoryUsage;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract  class Leak {

    public static int ONE_KILOBYTE = 1024;
    public static int ONE_MEGABYTE = ONE_KILOBYTE * ONE_KILOBYTE;
    public static int ONE_SECOND = 1000;

    public abstract  void run(String args[]);

    protected void sleep(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void printFreeMemory() {
        for (MemoryPoolMXBean item : ManagementFactory.getMemoryPoolMXBeans()) {
            String name = item.getName();
            MemoryUsage usage = item.getUsage();
            if (name.contains("Perm Gen") && name.contains("")) {
                System.out.printf("free permGen memory is %sKB\n", (usage.getMax() - usage.getUsed()) / ONE_KILOBYTE);
            }
        }
        System.out.printf("free heap memory is %sMB\n", getFreeMemory());
    }

    private long getFreeMemory() {
        return Runtime.getRuntime().freeMemory() / (ONE_MEGABYTE);
    }

}
