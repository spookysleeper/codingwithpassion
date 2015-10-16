package org.cwp;

import javassist.*;

public class ClassLeak extends Leak {

    @Override
    public void run(String[] args) {
        ClassPool pool = ClassPool.getDefault();
        int classIndex = 0;
        while(true) {
            createClass(pool, "Leak" + classIndex);
            classIndex++;
            if (classIndex % 250 == 0) {
                printFreeMemory();
            }
        }
    }

    private void createClass(ClassPool pool, String className) {
        try {
            CtClass leakClass = pool.makeClass(className);
            leakClass.addField(
                    CtField.make("private double numb = 0.0;", leakClass)
            );
            leakClass.addMethod(
                    CtNewMethod.make(
                            "public double eval (double x) { return x + numb ; }",
                            leakClass));
            leakClass.toClass();
        } catch (CannotCompileException exc) {
            exc.printStackTrace();
        }
    }

}
