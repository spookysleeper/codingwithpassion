package org.cwp;

import java.util.ArrayList;
import java.util.List;

//Simple heap leaks
//Refs: http://stackoverflow.com/questions/4062512/a-simple-program-to-demonstrate-memory-leak-in-java
//http://www.codeproject.com/Articles/30593/Effective-Java
//Primitive types sizes: http://btoddb-java-sizing.blogspot.sk/2012/01/object-sizes.html
//Class-loader leaks
public class RunByteLeak {

    private static final int ONE_MEGABYTE = 1024 * 1024;

	public static void main(String[] args) throws Exception {
        System.out.println("Run byte leak...");
        runByteTestLeak();
	}

    private static void runByteTestLeak() throws InterruptedException {
        List listOfBytes = new ArrayList();
        while(true) {
            //http://stackoverflow.com/questions/24559839/memory-usage-of-byte-array-in-java
            byte[] mb = new byte[ONE_MEGABYTE];
            listOfBytes.add(mb);
            Thread.sleep(100);
        }
    }

}