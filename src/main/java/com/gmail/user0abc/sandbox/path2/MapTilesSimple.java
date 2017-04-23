package com.gmail.user0abc.sandbox.path2;/* $Id$
 * Created by sergii.ivanov on 4/11/2017.
 */

public class MapTilesSimple implements IMapTiles
{
    int wX, wY;
    IMapTile[][] tiles;

    public MapTilesSimple(int widthX, int widthY)
    {
        wX = widthX;
        wY = widthY;
        tiles = new IMapTile[widthX][widthY];
    }

    @Override
    public IMapTile tileAt(int x, int y)
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
}
