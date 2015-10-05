package org.cwp;

import java.util.HashMap;
import java.util.Map;

//Simple heap leaks
//Refs: http://stackoverflow.com/questions/4062512/a-simple-program-to-demonstrate-memory-leak-in-java
//http://www.codeproject.com/Articles/30593/Effective-Java
//Primitive types sizes: http://btoddb-java-sizing.blogspot.sk/2012/01/object-sizes.html
//Class-loader leaks
//https://plumbr.eu/blog/locked-threads/how-to-shoot-yourself-in-foot-with-threadlocals
//http://www.eclipse.org/jetty/documentation/current/embedding-jetty.html
public abstract  class Leak {

    public static int ONE_MEGABYTE = 1024 * 1024;
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
        System.out.println("Free memory is " + getFreeMemory() + "MB");
    }

    private long getFreeMemory() {
        return Runtime.getRuntime().freeMemory() / (ONE_MEGABYTE);
    }

}
