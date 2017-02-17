package com.gmail.user0abc.sandbox.evo;/* {ID}
 * Created by sergii.ivanov on 2/17/2017.
 */

public class StandardGenReport implements GenReport
{
    int ticks = 0;
    Dove winner = null;

    @Override
    public void tick()
    {
        ticks++;
    }

    @Override
    public void setWinner(Dove dove)
    {
        winner = dove;
    }

    @Override
    public String toString()
    {
        return ticks + "\t" + winner;
    }
}
