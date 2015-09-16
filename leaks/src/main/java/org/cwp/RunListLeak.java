package org.cwp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RunListLeak {

    public static void main(String[] args) throws Exception {
        System.out.println("Run list leak...");
        runArrayListTest();
    }

    private static void runArrayListTest() throws InterruptedException {
        List<List> listOfLists = new ArrayList<List>();

        while(true) {
            List<BigDecimal> newArray = new ArrayList<BigDecimal>(1000);
            initializeList(newArray);
            listOfLists.add(newArray);
            Thread.sleep(100);
        }
    }

    private static void initializeList(List<BigDecimal> list) {
        for (int i = 0; i < 1000; i++) {
            list.add(new BigDecimal(Math.random() * 10000000));
        }
    }
}
