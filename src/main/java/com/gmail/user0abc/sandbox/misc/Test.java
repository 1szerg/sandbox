package com.gmail.user0abc.sandbox.misc;/* $Id$
 * Created by sergii.ivanov on 3/7/2017.
 */

import static com.gmail.user0abc.sandbox.Util.prn;

public class Test
{
    public static final String FOO = "foo";
    public static void main(String[] arg)
    {
        //testNullPointer();
        testSwitchString("KG");
        testSwitchString("foo");
        testSwitchString("---");
    }

    private static int testSwitchString(String in)
    {
        prn("testing "+in);
        WeightUnitOfMeasure wum = WeightUnitOfMeasure.byValue(in);
        prn(" wum = "+wum);
        switch(wum)
        {
            case KILOGRAMS:
                return 1;
            case LONG_TONS:
                return 0;
        }
        switch(in)
        {
            case FOO:
            case "GO":
                return -1;
            default:
                return 0;
        }
    }

    private static void testNullPointer()
    {

        try
        {
            String bebe = System.currentTimeMillis() % 2 == 0 ? null : "";
            int l = bebe.length();
        }
        catch(Exception e)
        {
            System.out.println("null");
        }

    }

}
