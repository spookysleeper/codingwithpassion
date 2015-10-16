package org.cwp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ListLeak extends Leak {

    private final static int NO_OF_ELEMENTS = 1000;

    @Override
    public void run(String[] args) {
        List<List> listOfLists = new ArrayList<List>();

        while(true) {
            List<BigDecimal> newArray = new ArrayList<BigDecimal>(NO_OF_ELEMENTS);
            initializeList(newArray);
            listOfLists.add(newArray);
            sleep(ONE_SECOND/10);
            printFreeMemory();
        }
    }

    private void initializeList(List<BigDecimal> list) {
        for (int i = 0; i < NO_OF_ELEMENTS; i++) {
            list.add(new BigDecimal(Math.random() * NO_OF_ELEMENTS * NO_OF_ELEMENTS));
        }
    }
}
