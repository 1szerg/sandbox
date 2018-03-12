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
            throw new IndexOutOfBoundsException("Coordinates ("+x+", "+y+") out of map size ["+wX+", "+wY+"]");
        }
        return tiles[x][y];
    }

    @Override
    public int sizeX()
    {
        return wX;
    }

    @Override
    public int sizeY()
    {
        return wY;
    }

    @Override
    public MapTiles fill()
    {
        for(int x = 0; x < wX; x++){
            for(int y = 0; y < wY; y++){
                tiles[x][y] = new SimpleMapTile(x, y, 1, true);
            }
        }
        return this;
    }

    @Override
    public MapTile setTileAt(int x, int y, MapTile mapTile)
    {
        MapTile old = tileAt(x, y);
        tiles[x][y] = mapTile;
        return null;
    }
}
