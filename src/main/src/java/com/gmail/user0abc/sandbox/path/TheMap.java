package com.gmail.user0abc.sandbox.path;/* {ID}
 * Created by sergii.ivanov on 2/21/2017.
 */

import java.util.Arrays;

public class TheMap
{
    public Tile[][] tiles;
    public int sizeX, sizeY;

    public TheMap(int x, int y)
    {
        sizeX = x;
        sizeY = y;
        tiles = new Tile[x][y];
        for(int i = 0; i < x; i++){
            for(int j = 0; j<y;j++){
                tiles[i][j] = new Tile(i,j);
            }
        }
    }

    public Tile tile(int x, int y)
    {
        return tiles[x][y];
    }
}
