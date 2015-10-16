package org.cwp;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.lang.reflect.Method;

import javassist.*;

public class RunLeak {

    public static void main(String [] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass leakClass = pool.makeClass("Leak");
        leakClass.addField(
                CtField.make("private static final byte[] space = new byte[200000];", leakClass)
        );
        leakClass.addMethod(
                CtNewMethod.make(
                        "public double eval (double x) { return 1.0d ; }",
                        leakClass));
        Class clazz = leakClass.toClass();
        Object obj = clazz.newInstance();
        Class[] formalParams = new Class[] { double.class };
        Method meth = clazz.getDeclaredMethod("eval", formalParams);
        Object[] actualParams = new Object[] { new Double(17) };
        double result = ((Double) meth.invoke(obj, actualParams)).doubleValue();
        System.out.println(result);

    }

}
