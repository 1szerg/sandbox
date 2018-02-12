package com.gmail.user0abc.sandbox.path2;/* $Id$
 * Created by sergii.ivanov on 4/11/2017.
 */

import java.util.List;

public interface PathFinder
{
    List<MapPath> calculateAllPaths(MapTiles map, MapTile start, MapTile finish);

    MapPath getBestPath(List<MapPath> path, PathComparator comparator);
}
