package com.gmail.user0abc.sandbox.path2.impl;

import com.gmail.user0abc.sandbox.path2.MapTile;

public class SimpleMapTile implements MapTile
{
    private int x;
    private int y;
    private int passCost;
    private boolean passable;

    public SimpleMapTile(int x, int y, int cost, boolean passable)
    {
        this.x = x;
        this.y = y;
        this.passCost = cost;
        this.passable = passable;
    }

    @Override
    public int getX()
    {
        return x;
    }

    @Override
    public int getY()
    {
        return y;
    }

    @Override
    public int getPassingCost()
    {
        return passCost;
    }

    @Override
    public boolean passable()
    {
        return passable;
    }
}
