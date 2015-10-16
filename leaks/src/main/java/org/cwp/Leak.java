package org.cwp;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryType;
import java.lang.management.MemoryUsage;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//Simple heap leaks
//Refs: http://stackoverflow.com/questions/4062512/a-simple-program-to-demonstrate-memory-leak-in-java
//http://www.codeproject.com/Articles/30593/Effective-Java
//Primitive types sizes: http://btoddb-java-sizing.blogspot.sk/2012/01/object-sizes.html
//Class-loader leaks
//https://plumbr.eu/blog/locked-threads/how-to-shoot-yourself-in-foot-with-threadlocals
//http://stackoverflow.com/questions/6470651/creating-a-memory-leak-with-java
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
