package com.gmail.user0abc.sandbox.evo;/* {ID}
 * Created by sergii.ivanov on 2/17/2017.
 */

import java.util.List;
import java.util.Random;

public class DefaultSim implements GenRunner
{
    Dove winner;
    GenReport report;

    @Override
    public GenReport runAndReport(DoveFactory doveFactory, Dove root, Random random, int population)
    {
        List<Dove> heap = doveFactory.generate(root, population, random);
        report = new StandardGenReport();
        winner = groomHeap(heap, random);
        return report;
    }

    private Dove groomHeap(List<Dove> heap, Random random)
    {
        while(heap.size()>1){
            tick(heap, random);
            report.tick();
        }
        report.setWinner(heap.get(0));
        return heap.get(0);
    }

    private void tick(List<Dove> heap, Random random)
    {
        int a = random.nextInt(heap.size());
        int b = random.nextInt(heap.size());
        float f = heap.get(a).maxD * random.nextFloat();
        float h = heap.get(b).h - f;
        if(h > 0){
            heap.get(b).h = h;
        }else{
            heap.remove(b);
        }

    }

    @Override
    public Dove winner()
    {
        return winner;
    }

}
