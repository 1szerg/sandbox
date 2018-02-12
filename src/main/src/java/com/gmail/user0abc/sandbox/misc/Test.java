package com.gmail.user0abc.sandbox.misc;/* $Id$
 * Created by sergii.ivanov on 3/7/2017.
 */

import java.io.*;

import static com.gmail.user0abc.sandbox.Util.prn;

public class Test
{
    public static void main(String[] arg) throws InterruptedException
    {
        //sleepThreads();
        finallyReturnTest();
    }

    private static void finallyReturnTest()
    {
        prn("returned "+callTry(1));
        prn("returned "+callTry(0));
        prn("returned "+callTry(-1));
    }

    private static String callTry(int d)
    {
        prn("d="+d);
        try {
            return String.valueOf(1 / d);
        } catch (Throwable t) {
            return String.valueOf("cant divide");
        } finally {
            prn("finally");
            if(d<0){
                return "below";
            }
        }
    }

    private static void sleepThreads() throws InterruptedException
    {
        for (int i = 0; i < 10; i++) {
            prn(testNullPointer());
            Thread.currentThread().sleep(9L);
        }
    }

    private static String testNullPointer()
    {
        File f = new File("travel-docs-ua-_0001.jpg");
        prn("reading " + f.getAbsolutePath());
        try (InputStream stream = new FileInputStream(f)) {

            byte[] bytes = new byte[1024];
            stream.read(bytes);
            return bytes.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
//        finally {
//            return "finally";
//        }

    }

}
