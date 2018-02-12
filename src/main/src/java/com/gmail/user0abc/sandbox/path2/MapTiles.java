package com.gmail.user0abc.sandbox.path2;/* $Id$
 * Created by sergii.ivanov on 4/11/2017.
 */

public interface MapTiles
{
    MapTile tileAt(int x, int y);

    int sizeX();

    int sizeY();

    MapTiles fill();
}
