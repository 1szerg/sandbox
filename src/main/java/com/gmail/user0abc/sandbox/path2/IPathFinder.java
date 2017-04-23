package com.gmail.user0abc.sandbox.path2;/* $Id$
 * Created by sergii.ivanov on 4/11/2017.
 */

import java.util.List;

public interface IPathFinder
{
    List<IMapPath> calculateAllPaths(IMapTiles map, IMapTile start, IMapTile finish);

    IMapPath getBestPath(List<IMapPath> path, IPathComparator comparator);
}
