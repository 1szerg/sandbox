package com.gmail.user0abc.sandbox.path2.impl;/* $Id$
 * Created by sergii.ivanov on 4/11/2017.
 */

import com.gmail.user0abc.sandbox.path2.MapTile;
import com.gmail.user0abc.sandbox.path2.MapTiles;

import java.util.Arrays;

public class SimpleMapTiles implements MapTiles
{
    int wX, wY;
    MapTile[][] tiles;

    public SimpleMapTiles(int widthX, int widthY)
    {
        wX = widthX;
        wY = widthY;
        tiles = new MapTile[widthX][widthY];
    }

    @Override
    public MapTile tileAt(int x, int y)
    {
        if(x < 0 || x > (wX - 1) || y < 0 || y > (wY - 1)){
            throw new IndexOutOfBoundsException("Coordinates  Out of map size ");
        }
        return tiles[x][y];
    }

    @Override
    public int sizeX()
    {
        return 0;
    }

    @Override
    public int sizeY()
    {
        return 0;
    }

    @Override
    public MapTiles fill()
    {
        Arrays.fill(tiles, new );
        for(int i = 0; i < wX; i++){
            for(int y)
        }
        return null;
    }
}
