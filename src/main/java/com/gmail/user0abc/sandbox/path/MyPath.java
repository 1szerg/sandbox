package com.gmail.user0abc.sandbox.path;/* {ID}
 * Created by sergii.ivanov on 2/22/2017.
 */

import java.util.List;

public class MyPath
{
    Tile start, finish;
    List<Tile> path;
    boolean passable;
    boolean calculated;

    public MyPath(Tile start, Tile finish)
    {
        this.start = start;
        this.finish = finish;
        calculated = false;
    }
}
