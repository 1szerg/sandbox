package com.gmail.user0abc.sandbox.path2.impl;/* $Id$
 * Created by sergii.ivanov on 4/11/2017.
 */

import com.gmail.user0abc.sandbox.path2.MapGenerator;
import com.gmail.user0abc.sandbox.path2.MapTiles;

import java.util.Random;

public class SimpleMapGenerator implements MapGenerator
{
    private float density;

    public SimpleMapGenerator(float density)
    {
        this.density = density;
    }

    @Override
    public MapTiles makeMap(int widthX, int widthY, Random random)
    {
        MapTiles map = new SimpleMapTiles(widthX, widthY).fill();
        long sq = widthX * widthY;

        return null;
    }
}
