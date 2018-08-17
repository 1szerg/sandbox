package com.gmail.user0abc.sandbox.evo;/* {ID}
 * Created by sergii.ivanov on 2/17/2017.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main
{
    static int N = 1000;
    static int gens = 1000;
    static StringBuilder builder = new StringBuilder();

    public static void main(String[] args)
    {
        //new Main().getTest();
        new Main().caster();
    }

    private void getTest(){
        long t = System.currentTimeMillis();
        prn("gen\ttime\th\td");
        runNGens(gens, new Dove().setMaxH(100f).setMaxD(25f));
        prn("time = " + (System.currentTimeMillis() - t) + " ms");
        try
        {
            File f = new File(System.currentTimeMillis() + "-" + gens + ".log");
            f.createNewFile();
            FileOutputStream stream = new FileOutputStream(f);
            stream.write(builder.toString().getBytes());
            stream.flush();
            prn("saved to "+f.getAbsolutePath());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void prn(String s)
    {
        System.out.println(s);
        builder.append(s).append("\n");
    }

    public void runNGens(int n, Dove root)
    {
        //List<GenReport> reports = new ArrayList<>();
        int g = 0;
        while(n > g)
        {
            GenRunner genRunner = getRunner();
            GenReport report = genRunner.runAndReport(getDoveFactory(), root, new Random(), N);
            //reports.add(report);
            root = genRunner.winner();
            prn(g + "\t" + report.toString());
            g++;
        }
        //return reports;
    }

    private DoveFactory getDoveFactory()
    {
        return new DoveFactoryImpl();
    }

    private GenRunner getRunner()
    {
        return new DefaultSim();
    }

    private void caster(){
        List<Class<?>> l1 = new ArrayList<>();
        List<Class<Object>> l2 = new ArrayList<>();
        List<Class> l3 = new ArrayList<>();

        l1.add(Thread.class);

        l2.add(Object.class);

        l3.addAll(l1);
        l3.add(System.class);

        for(Class<?> o: l1)
        {
            l2.add((Class<Object>) o);
        }

        System.out.println("L1 [Thread] = " + l1);
        System.out.println("L2 [Object + L1] = " + l2);
        System.out.println("L3 [L1 + System] = " + l3);
   }

}
