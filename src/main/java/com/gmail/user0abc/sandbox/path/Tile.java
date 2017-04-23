package com.gmail.user0abc.sandbox.path;/* {ID}
 * Created by sergii.ivanov on 2/21/2017.
 */

public class Tile
{
    int x,y;
    boolean blocked;
    boolean path;
    public boolean start, finish;

    public Tile(int x, int y)
    {
        this.x = x;
        this.y = y;
        blocked = false;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;

        Tile tile = (Tile) o;

        if(x != tile.x)
            return false;
        return y == tile.y;
    }

    @Override
    public int hashCode()
    {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
