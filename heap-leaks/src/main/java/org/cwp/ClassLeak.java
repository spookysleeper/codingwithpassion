package org.cwp;

import javassist.*;

import java.lang.reflect.Method;

public class ClassLeak extends Leak {

    @Override
    public void run(String[] args) {
        Class clazz = createClass("Leak");
        invokeMethod(clazz);
    }

    private Class createClass(String className) {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass leakClass = pool.makeClass("Leak");
            leakClass.addField(
                    CtField.make("private static final byte[] space = new byte[200000];", leakClass)
            );
            leakClass.addMethod(
                    CtNewMethod.make(
                            "public double eval (double x) { return 1.0d ; }",
                            leakClass));
            return leakClass.toClass();
        } catch (CannotCompileException exc) {
            exc.printStackTrace();
            return null;
        }
    }

    private void invokeMethod(Class clazz) {
        try {
            Object obj = clazz.newInstance();
            Class[] formalParams = new Class[] { double.class };
            Method meth = clazz.getDeclaredMethod("eval", formalParams);
            Object[] actualParams = new Object[] { new Double(17) };
            double result = ((Double) meth.invoke(obj, actualParams)).doubleValue();
            System.out.println(result);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

}
