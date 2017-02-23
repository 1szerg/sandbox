package com.gmail.user0abc.sandbox.path;/* {ID}
 * Created by sergii.ivanov on 2/21/2017.
 */

import java.util.ArrayList;
import java.util.List;

public class SimplePathFinder
{
    public void find(MyPath path)
    {
        path.path = new ArrayList<>();
        final double distance = MapUtil.dist(path.start, path.finish);
        Tile curent = path.start;
        int limitSteps = (int)distance * 2;
        while(limitSteps > 0){
            limitSteps--;
            path.path.add(curent);
            List<Tile> next = getNextTile(curent, path.finish);
        }
    }

    private List<Tile> getNextTile(Tile curent, Tile finish)
    {
        List<Tile> next = new ArrayList<>();

        return next;
    }
}
