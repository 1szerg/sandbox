package com.gmail.user0abc.sandbox.path2;/* $Id$
 * Created by sergii.ivanov on 4/11/2017.
 */

import java.util.Random;

public class Main
{

    public static void main(String... args)
    {
        IMapGenerator gen = new MapGeneratorSimple();
        Random r = new Random();
        IMapTiles map = gen.makeMap(10,20, r);
    }

    public IMapTiles makeMap(int x, int y, Random random)
    {
        return null;
    }

    static class PathfinderTest
    {

        public int testPathFinder(IPathFinder IPathFinder, IMapTiles map, IPathComparator comparator, IMapTile start, IMapTile finish){
            return comparator.getPathCost(
                    IPathFinder.getBestPath(IPathFinder.calculateAllPaths(map, start, finish), comparator));
        }

    }

    class TestResult{

    }


}
