package org.cwp;

public class RunLeak {

    public static void main(String [] args) throws Exception {
        String className = args[0];
        Class leakClass = Class.forName("org.cwp." + className);
        Leak leak = (Leak)leakClass.newInstance();
        leak.run(args);
    }

}
