package com.gmail.user0abc.sandbox.path;/* $Id$
 * Created by sergii.ivanov on 2/23/2017.
 */

public class MapUtil
{
    public static Double dist(Tile start, Tile finish)
    {
        return Math.sqrt((start.x - finish.x)*(start.x - finish.x)+(start.y - finish.y)*(start.y - finish.y));
    }
}
