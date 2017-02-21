package com.gmail.user0abc.sandbox.path;/* {ID}
 * Created by sergii.ivanov on 2/21/2017.
 */

import java.util.Random;

public class PathfinderTest
{
    public TheMap runMap(int sizeX, int sizeY, int blocks, SimplePathFinder simplePathFinder)
    {
        TheMap map = new TheMap(sizeX, sizeY);
        fillBlocks(map, blocks);
        return map;
    }

    private void fillBlocks(TheMap map, int blocks)
    {
        Random rand = new Random();
        while(blocks > 0){
            int x = rand.nextInt(map.sizeX);
            int y = rand.nextInt(map.sizeY);
            if(!map.tile(x,y).blocked){
                blocks--;
                map.tile(x,y).blocked = true;
            }
        }
    }
}
