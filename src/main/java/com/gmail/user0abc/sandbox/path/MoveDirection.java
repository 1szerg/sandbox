package com.gmail.user0abc.sandbox.path;/* $Id$
 * Created by sergii.ivanov on 2/28/2017.
 */

public class MoveDirection
{
    int dX, dY;
    public MoveDirection(int dX, int dY)
    {
        this.dX = dX;
        this.dY = dY;
    }

    public static MoveDirection newDirection(Tile curent, Tile finish)
    {
        int dX = finish.x - curent.x;
        int dY = finish.y - curent.y;
        if(Math.abs(dX) > Math.abs(dY)){
            return new MoveDirection((int)Math.signum(dX), 0);
        }
        return new MoveDirection(0, (int)Math.signum(dY));
    }
}
