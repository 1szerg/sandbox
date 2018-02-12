package com.gmail.user0abc.sandbox.evo;/* {ID}
 * Created by sergii.ivanov on 2/17/2017.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.rmi.server.ExportException;
import java.util.Random;

public class Main
{
    static int N = 1000;
    static int gens = 1000;
    static StringBuilder builder = new StringBuilder();

    public static void main(String[] args)
    {
        new Main().getTest();
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

}
