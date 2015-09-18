package org.cwp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RunMapKeyLeak {

    public static void main(String[] args) throws Exception {
        Map<GoodKey, StringBuilder> map = new HashMap<GoodKey, StringBuilder>(1000);
        int counter = 0;
        while (true) {
            // creates duplicate objects due to bad Key class
            map.put(new GoodKey("dummyKey"), new StringBuilder(1000));
            counter++;
            if (counter % 1000 == 0) {
                System.out.println("Map size: " + map.size());
                System.out.println("Free memory after count " + counter
                        + " is " + getFreeMemory() + "MB");
                Thread.sleep(1000);
            }
        }
    }

    // inner class key without hashcode() or equals()
    // bad implementation causes memory leak
    static class WrongKey {
        private String dummyValue;
        public WrongKey(String dummyValue) {
            this.dummyValue = dummyValue;
        }
    }

    static class GoodKey {
        private String dummyValue;

        public GoodKey(String dummyValue) {
            this.dummyValue = dummyValue;
        }

        @Override
        public int hashCode() {
            return dummyValue.hashCode();
        }

        @Override
        public boolean equals(Object anotherValue) {
            GoodKey anotherGoodKeyValue = (GoodKey)anotherValue;
            return dummyValue.equals(anotherGoodKeyValue.dummyValue);
        }
    }

    public static long getFreeMemory() {
        return Runtime.getRuntime().freeMemory() / (1024 * 1024);
    }

}
