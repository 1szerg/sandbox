package com.gmail.user0abc.sandbox.path2;/* $Id$
 * Created by sergii.ivanov on 4/11/2017.
 */

import com.gmail.user0abc.sandbox.path2.impl.SimpleMapGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main
{

    public static void main(String... args)
    {
        new Main().doTest();
    }

    private void doTest()
    {
        MapTiles map = makeMap(new SimpleMapGenerator(0.5f), new Random(), 10, 20);


    }

    private MapTiles makeMap(MapGenerator gen, Random r, int x, int y){
        return gen.makeMap(x, y, r);
    }

}
