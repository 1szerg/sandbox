package com.gmail.user0abc.sandbox.path;/* $Id$
 * Created by sergii.ivanov on 2/23/2017.
 */

import java.util.List;

public class MapUtil
{
    public static Double dist(Tile start, Tile finish)
    {
        return Math.sqrt((start.x - finish.x)*(start.x - finish.x)+(start.y - finish.y)*(start.y - finish.y));
    }

    public static Tile move(TheMap map, Tile curent, MoveDirection dir)
    {
        return map.tile(curent.x+dir.dX, curent.y+dir.dY);
    }

}
