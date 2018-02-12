package com.gmail.user0abc.sandbox.path;/* {ID}
 * Created by sergii.ivanov on 2/22/2017.
 */

import java.util.ArrayList;
import java.util.Collections;
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

    public MyPath copy(){
        MyPath copy = new MyPath(start, finish);
        copy.calculated = this.calculated;
        Collections.copy(copy.path, this.path);
        return copy;
    }
}
