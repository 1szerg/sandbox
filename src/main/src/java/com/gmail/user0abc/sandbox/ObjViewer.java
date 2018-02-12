package com.gmail.user0abc.sandbox;

import java.lang.reflect.Field;

public class ObjViewer {

    static public void printObject(Object o, int level) {
        if(o == null || level > 10){
            return;
        }
        Class cls = o.getClass();
        if (cls == Object.class) {
            return;
        }
        prn(tab(level) + cls.getCanonicalName());
        level++;
        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            Class fType = f.getType();
            f.setAccessible(true);
            if (fType.isPrimitive()) {
                prn(tab(level) + printPrimitive(o, f, fType));
            }else if("Integer Long Float Boolean String Byte Char ".contains(fType.getSimpleName())){
                try {
                    prn(tab(level) + f.getName() + "(" + fType.getSimpleName() + ") = " + f.get(o));
                } catch (IllegalAccessException e) {
                    prn(tab(level) + f.getName() + "(" + fType.getSimpleName() + ") = <no access>");
                }
            }else if(fType.isArray()){
                try {
                    prn(tab(level)+f.getName()+"[ "+f.get(o)+" ]");
                } catch (IllegalAccessException e) {
                    prn(tab(level)+ f.getName() + "[] = <no access>");
                }
            }
            else{
                try {
                    printObject(f.get(o), level);
                } catch (IllegalAccessException e) {
                    prn(tab(level)+ f.getName() + " = <no access>");
                }
            }
        }
    }

    private static String printPrimitive(Object o, Field f, Class fType) {
        switch (fType.getName()) {
            case "int": {
                try {
                    return f.getName() + "(" + fType + ") = " + f.getInt(o);
                } catch (IllegalAccessException e) {
                    return f.getName() + "(" + fType + ") = <no access>";
                }
            }
            case "long":{
                try {
                    return f.getName() + "(" + fType + ") = " + f.getLong(o);
                } catch (IllegalAccessException e) {
                    return f.getName() + "(" + fType + ") = <no access>";
                }
            }
            case "float":{
                try {
                    return f.getName() + "(" + fType + ") = " + f.getFloat(o);
                } catch (IllegalAccessException e) {
                    return f.getName() + "(" + fType + ") = <no access>";
                }
            }
            case "boolean":{
                try {
                    return f.getName() + "(" + fType + ") = " + f.getBoolean(o);
                } catch (IllegalAccessException e) {
                    return f.getName() + "(" + fType + ") = <no access>";
                }
            }
            case "byte":{
                try {
                    return f.getName() + "(" + fType + ") = " + f.getByte(o);
                } catch (IllegalAccessException e) {
                    return f.getName() + "(" + fType + ") = <no access>";
                }
            }
            case "char":{
                try {
                    return f.getName() + "(" + fType + ") = " + f.getChar(o);
                } catch (IllegalAccessException e) {
                    return f.getName() + "(" + fType + ") = <no access>";
                }
            }
            case "short":{
                try {
                    return f.getName() + "(" + fType + ") = " + f.getShort(o);
                } catch (IllegalAccessException e) {
                    return f.getName() + "(" + fType + ") = <no access>";
                }
            }
            default: {
                try {
                    return f.getName() + "(" + fType + ") = " + f.get(o);
                } catch (IllegalAccessException e) {
                    return f.getName() + "(" + fType + ") = <no access>";
                }
            }
        }
    }

    static private String tab(int i) {
        StringBuilder b = new StringBuilder("");
        while (i > 0) {
            b.append("  ");
            i--;
        }
        return b.toString();
    }

    static private void prn(String msg) {
        System.out.println(msg);
    }

    public static void main(String[] a) {
        TestObject t = new TestObject();
        printObject(t, 0);
    }

    public static class TestObject {
        Integer int1 = 10;
        int int2 = 20;
        Incl include = new Incl();
    }

    public static class Incl {
        String str = "test";
        boolean[] ps = {true, false, true};
    }

}
