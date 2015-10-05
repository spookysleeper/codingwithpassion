package org.cwp;

import java.util.ArrayList;
import java.util.List;

public class ByteLeak extends Leak {

    @Override
    public void run(String[] args) {
        List listOfBytes = new ArrayList();
        while(true) {
            //http://stackoverflow.com/questions/24559839/memory-usage-of-byte-array-in-java
            byte[] mb = new byte[ONE_MEGABYTE];
            listOfBytes.add(mb);
            sleep(ONE_SECOND / 10);
            printFreeMemory();
        }
    }


}