package org.cwp;

import java.util.HashMap;
import java.util.Map;

public class MapKeyLeak extends Leak {

    private final static int NO_OF_ELEMENTS = 1000;

    @Override
    public void run(String[] args) {
        Map<Object, StringBuilder> map = new HashMap<Object, StringBuilder>(NO_OF_ELEMENTS);
        boolean goodKey = args.length == 2 && args[1] != null;
        while (true) {
            map.put(getKey(goodKey), new StringBuilder(NO_OF_ELEMENTS * 100));
            System.out.println("Map size: " + map.size());
            printFreeMemory();
            sleep(ONE_SECOND / 10);
        }
    }

    private Object getKey(boolean goodKey) {
        String dummyKeyValue = "dummyKey";
        if (goodKey) {
            return new GoodKey(dummyKeyValue);
        } else {
            return new KeyWithoutHashCodeOrEquals(dummyKeyValue);
        }
    }

    static class KeyWithoutHashCodeOrEquals {
        private String dummyValue;
        public KeyWithoutHashCodeOrEquals(String dummyValue) {
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


}
