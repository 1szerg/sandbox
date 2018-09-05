package com.gmail.user0abc.sandbox.performance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Solution2
{

    private static int[] health;
    private static String[] genes;

    private static Scanner scanner;

    public static void main(String[] args) {
        try
        {
            File file = new File("input02.txt");
            System.out.println("file "+file.getAbsolutePath());
            scanner = new Scanner(new FileInputStream(file));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        genes = new String[n];

        String[] genesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            String genesItem = genesItems[i];
            genes[i] = genesItem;
        }

        health = new int[n];

        String[] healthItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int healthItem = Integer.parseInt(healthItems[i]);
            health[i] = healthItem;
        }

        int s = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        Timer t = new Timer();t.start();
        Timer t1 = new Timer();
        Timer t2 = new Timer();
        ExecutorService service = Executors.newFixedThreadPool(64);
        List<Future<Integer>> pool = new LinkedList<>();
        for (int sItr = 0; sItr < s; sItr++) {
            t1.start();
            String[] firstLastd = scanner.nextLine().split(" ");

            int first = Integer.parseInt(firstLastd[0]);

            int last = Integer.parseInt(firstLastd[1]);

            String d = firstLastd[2];
            t1.stop();
            t2.start();
            pool.add(service.submit(new Gnome(d, first, last)));
            t2.stop();
        }
        scanner.close();
        t.stop();
        System.out.println("t1="+t.get()+" pool="+pool.size()+" "+t1.get()+" / "+t2.get());
        t=new Timer();t.start();
        service.shutdown();
        int theBest = -1;
        int theWorst = -1;
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
        t.stop();
        System.out.println("t2="+t.get());

        System.out.println(theWorst+" "+theBest);
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
            int score = 0;
            for(int i = first; i <= last; i++){
                int pos = 0; int index;
                while(pos < d.length() && (index = d.indexOf(genes[i], pos)) >= 0){
                    pos = index+1;
                    score += health[i];
                }
            }
            return score;
        }
    }

    static class Timer{
        private long total = 0L, t=0L;
        void start(){t = System.currentTimeMillis();}
        void stop(){total += (System.currentTimeMillis()-t);}
        long get(){return total;}
    }

}