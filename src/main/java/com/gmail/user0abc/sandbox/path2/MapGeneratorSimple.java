package com.gmail.user0abc.sandbox.path2;/* $Id$
 * Created by sergii.ivanov on 4/11/2017.
 */

import java.util.Random;

public class MapGeneratorSimple implements IMapGenerator
{
    @Override
    public IMapTiles makeMap(int widthX, int widthY, Random random)
    {
        IMapTiles map = new MapTilesSimple(widthX, widthY);
        return null;
    }
}
