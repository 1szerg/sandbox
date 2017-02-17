package com.gmail.user0abc.sandbox.evo;/* {ID}
 * Created by sergii.ivanov on 2/17/2017.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DoveFactoryImpl implements DoveFactory
{
    @Override
    public List<Dove> generate(Dove root, int population, Random random)
    {
        List<Dove> heap = new ArrayList<>(population);
        while(heap.size() < population)
        {
            heap.add(createDove(root, random));
        }
        return heap;
    }

    private Dove createDove(Dove root, Random random)
    {
        Dove dove = new Dove();
        dove.maxD = root.maxD + random.nextFloat() - 0.499f;
        if(dove.maxD < 0.01f)
        {
            dove.maxD = 0.01f;
        }
        dove.maxH = root.maxH + random.nextFloat() - 0.499f;
        dove.h = dove.maxH;
        return dove;
    }
}
