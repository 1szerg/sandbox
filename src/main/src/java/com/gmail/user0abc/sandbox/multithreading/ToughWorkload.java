package com.gmail.user0abc.sandbox.multithreading;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ToughWorkload
{
    static int[] health;
    static String[] genes;
    static Random r = new Random();

    @Test
    public void toughWorkTest()
    {
        final int N = 1000;
        String[] inputs = new String[N];
        for(int i = 0; i < N; i++){
            inputs[i] = randString();
        }
        ExecutorService service = Executors.newFixedThreadPool(8);
        List<Future<Integer>> pool = new LinkedList<>();
        for(int i = 0; i < N; i++){
            pool.add(service.submit(new Gnome(inputs[i], 0, 6)));
        }
        service.shutdown();
        int theWorst = -1, theBest =-1;
        for (Future<Integer> res: pool){
            int val = 0;
            try
            {
                val = res.get();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            } catch (ExecutionException e)
            {
                e.printStackTrace();
            }
            if(val > theBest || theBest < 0) theBest = val;
            if(val < theWorst || theWorst < 0) theWorst = val;
        }
    }

    private String randString()
    {
        int lim = r.nextInt(100);
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < lim; i++){
            builder.append((char)r.nextInt(24)+32);
        }
        return builder.toString();
    }

    static class Gnome implements Callable<Integer>
    {

        String d;
        int first,last;

        public Gnome(String d, int first, int last)
        {
            this.d = d;
            this.first = first;
            this.last = last;
        }

        @Override
        public Integer call() throws Exception
        {
            return calcBestGene(first, last, d);
        }
    }

    private static int calcBestGene(int first, int last, String d){
        int score = 0;
        //System.out.println(" > "+d+" ("+first+", "+last+")");
        for(int i = first; i <= last; i++){
            int pos = 0; int index;
            //System.out.println(genes[i]+" ? "+health[i] +" in "+d.substring(pos));
            while(pos < d.length() && (index = d.indexOf(genes[i], pos)) >= 0){
                //System.out.println(genes[i]+" > "+index);
                pos = index+1;
                score += health[i];
            }
        }
        //System.out.println(" = "+score);
        return score;
    }
}
