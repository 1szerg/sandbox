package com.gmail.user0abc.sandbox.path;/* {ID}
 * Created by sergii.ivanov on 2/21/2017.
 */

import java.util.ArrayList;
import java.util.Random;

public class PathfinderTest
{
    public TheMap runMap(int sizeX, int sizeY, int blocks, PathFinderEngine pathFinder)
    {
        TheMap map = new TheMap(sizeX, sizeY);
        fillBlocks(map, blocks);
        Tile start = getRandomTile(map, 0);
        Tile finish = getRandomTile(map, map.sizeX-1);
        start.start = true;
        finish.finish = true;
        MyPath path = pathFinder.find(start, finish, map);
        applyPath(map, path);
        return map;
    }

    private Tile getRandomTile(TheMap map, int x)
    {
        Random r = new Random();
        for(int limit = map.sizeY * 10; limit > 0; limit--)
        {
            Tile t = map.tile(x, r.nextInt(map.sizeY));
            if(!t.blocked){
                return t;
            }
        }
        return null;
    }

    private void applyPath(TheMap map, MyPath path)
    {
        if(path == null || path.path == null || path.path.size() == 0){
            return;
        }
        for(Tile tile : path.path)
        {
            map.tile(tile.x, tile.y).path = true;
        }
    }

    private void fillBlocks(TheMap map, int blocks)
    {
        Random rand = new Random();
        while(blocks > 0)
        {
            int x = rand.nextInt(map.sizeX);
            int y = rand.nextInt(map.sizeY);
            if(!map.tile(x, y).blocked)
            {
                blocks--;
                map.tile(x, y).blocked = true;
            }
        }
    }
}
