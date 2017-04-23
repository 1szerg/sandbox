package com.gmail.user0abc.sandbox.path2;/* $Id$
 * Created by sergii.ivanov on 4/11/2017.
 */

import java.util.Random;

public interface IMapGenerator
{
    IMapTiles makeMap(int widthX, int widthY, Random random);
}
