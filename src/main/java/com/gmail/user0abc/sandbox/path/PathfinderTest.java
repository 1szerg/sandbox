package com.gmail.user0abc.sandbox.path;/* {ID}
 * Created by sergii.ivanov on 2/21/2017.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PathfinderTest
{
    public TheMap runMap(int sizeX, int sizeY, int blocks, SimplePathFinder pathFinder)
    {
        TheMap map = new TheMap(sizeX, sizeY);
        fillBlocks(map, blocks);
        List<MyPath> allPaths = getAllPossiblePaths(map);
        for(MyPath path: allPaths){
            pathFinder.find(path);
        }
        return map;
    }

    private List<MyPath> getAllPossiblePaths(TheMap map)
    {
        List<MyPath> result = new ArrayList<>();
        for(int i = 0; i < map.sizeX; i++){
            if(!map.tile(i,0).blocked){
                for(int j=0; j<map.sizeX; j++){
                    if(!map.tile(j,map.sizeY-1).blocked){
                        result.add(new MyPath(map.tile(i,0), map.tile(j,map.sizeY-1)));
                    }
                }
            }
        }
        return null;
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
