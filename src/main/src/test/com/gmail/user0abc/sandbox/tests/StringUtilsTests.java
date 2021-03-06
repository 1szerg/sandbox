package com.gmail.user0abc.sandbox.tests;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class StringUtilsTests
{
    @Test
    public void splitTest()
    {
        final int N = 10000;
        String trash = "";
        String[] addresses = new String[N];
        Random r = new Random(100L);
        for (int i = 0; i < N; i++)
        {
            addresses[i] = buildIPv6(r);
        }
        System.out.println("having " + addresses.length + " IPs like " + addresses[0]);
        int T1 = 0;
        int T2 = 0;
        for (int k = 0; k < 50; k++)
        {
            long t1 = System.currentTimeMillis();
            for (int i = 0; i < addresses.length; i++)
            {
                String[] parts = addresses[i].split(":");
                for (String p : parts)
                {
                    trash = p;
                }
            }
            t1 = System.currentTimeMillis() - t1;

            long t2 = System.currentTimeMillis();
            for (int i = 0; i < addresses.length; i++)
            {
                StringTokenizer tokenizer = new StringTokenizer(addresses[i], ":");
                while (tokenizer.hasMoreElements())
                {
                    trash = tokenizer.nextToken();
                }
            }
            t2 = System.currentTimeMillis() - t2;
            T1 += t1;
            T2 += t2;
            //System.out.println("t1 = " + t1 + " t2 = " + t2);
        }
        System.out.println("T1(split) = " + T1 + " ms T2(tokenizer) = " + T2 + " ms");
    }

    private String buildIPv6(Random r)
    {
        String[] addr = new String[8];
        for (int i = 0; i < 8; i++)
        {
            addr[i] = Integer.toHexString(r.nextInt(0xffff + 1));
        }
        return Arrays.stream(addr).collect(Collectors.joining(":"));
    }

    @Test
    public void indexOfVScontains()
    {
        final String[] hays = {
                "ffff:ffff:ffff:ffff:ffff:ffff:ffff:ffff",
                "aaaa:aaaa:aaaa:aaaa:aaaa:aaaa:aaaa:aaaa",
                "ffff:ffff:ffff:ffff:ffff:ffff:ffff:ffff",
                "aaaa:aaaa:aaaa:aaaa:aaaa:aaaa:aaaa:aaaa",
                "ffff:ffff:ffff:ffff:ffff:ffff:ffff:ffff",
                "aaaa:aaaa:aaaa:aaaa:aaaa:aaaa:aaaa:aaaa",
                "ffff:ffff:ffff:ffff:ffff:ffff:ffff:ffff",
                "aaaa:aaaa:aaaa:aaaa:aaaa:aaaa:aaaa:aaaa",
                "ffff:ffff:ffff:ffff:ffff:ffff:ffff:ffff",
                "aaaa:aaaa:aaaa:aaaa:aaaa:aaaa:aaaa:aaaa",
                "ffff:ffff:ffff:ffff:ffff:ffff:ffff:ffff",
                "aaaa:aaaa:aaaa:aaaa:aaaa:aaaa:aaaa:aaaa",
                "ffff:ffff:ffff:ffff:ffff:ffff:ffff:ffff",
                "aaaa:aaaa:aaaa:aaaa:aaaa:aaaa:aaaa:aaaa",
                "ffff:ffff:ffff:ffff:ffff:ffff:ffff:ffff",
                "aaaa:aaaa:aaaa:aaaa:aaaa:aaaa:aaaa:aaaa",
                "ffff:ffff:ffff:ffff:ffff:ffff:ffff:ffff",
                "aaaa:aaaa:aaaa:aaaa:aaaa:aaaa:aaaa:aaaa",
                "ffff:ffff:ffff:ffff:ffff:ffff:ffff:ffff",
                "aaaa:aaaa:aaaa:aaaa:aaaa:aaaa:aaaa:aaaa",
        };
        final int N = hays.length;
        int T1=0, T2=0;
        for (int b = 0; b < 100000; b++)
        {

            long t1 = System.currentTimeMillis();
            for (int i = 0; i < N; i++)
            {
                int in = hays[i % hays.length].indexOf(":");
            }
            t1 = System.currentTimeMillis() - t1;
            T1 += t1;

            long t2 = System.currentTimeMillis();
            for (int i = 0; i < N; i++)
            {
                boolean in = hays[i % hays.length].contains(":");
            }
            t2 = System.currentTimeMillis() - t2;
            T2 += t2;

        }
        System.out.println("T1 = " + T1 + " T2=" + T2);


    }

    @Test
    public void sortStringArr(){
        String[] s = new String[]{"aa","ccc","a","b","cc","bb","c"};
        Arrays.sort(s);
        System.out.println(Arrays.stream(s).collect(Collectors.joining(", ")));
    }

}
