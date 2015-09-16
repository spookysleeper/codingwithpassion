package org.cwp;

import java.util.ArrayList;
import java.util.List;

//Simple heap leaks
//Refs: http://stackoverflow.com/questions/4062512/a-simple-program-to-demonstrate-memory-leak-in-java
//http://www.codeproject.com/Articles/30593/Effective-Java
public class RunByteLeak {

	public static void main(String[] args) throws Exception {
        System.out.println("Run byte leak...");
        runByteTestLeak();
	}

    private static void runByteTestLeak() throws InterruptedException {
        List listOfBytes = new ArrayList();
        while(true) {
            byte[] b = new byte[1024*1024];
            listOfBytes.add(b);
            Thread.sleep(100);
        }
    }

}