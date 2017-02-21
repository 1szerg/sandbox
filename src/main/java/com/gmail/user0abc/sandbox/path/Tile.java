package com.gmail.user0abc.sandbox.path;/* {ID}
 * Created by sergii.ivanov on 2/21/2017.
 */

public class Tile
{
    int x,y;
    boolean blocked;
    boolean path;

    public Tile(int x, int y)
    {
        this.x = x;
        this.y = y;
        blocked = false;
    }
}
