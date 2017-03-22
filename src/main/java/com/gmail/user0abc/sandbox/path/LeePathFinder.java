package com.gmail.user0abc.sandbox.path;/* {ID}
 * Created by sergii.ivanov on 2/21/2017.
 */

import java.util.ArrayList;
import java.util.List;

import static com.gmail.user0abc.sandbox.path.Pathfinder.prn;

public class LeePathFinder implements PathFinderEngine
{
    public MyPath find(Tile start, Tile finish, TheMap map)
    {
        int[][] evalMap = new int[map.sizeX][map.sizeY];
        evaluateMap(evalMap, start, finish, map);
        drawEvalMap(evalMap);
        return null;
    }

    private MyPath evaluateMap(int[][] evalMap, Tile start, Tile finish, TheMap map)
    {
        final double distance = MapUtil.dist(start, finish);
        int limitSteps = (int) distance * 6;
        prn("distance = " + distance + " limit = " + limitSteps);
        int step = 1;
        evalMap[start.x][start.y] = 1;
        while(limitSteps > step)
        {
            for(int x = 0; x < map.sizeX; x++)
            {
                for(int y = 0; y < map.sizeY; y++)
                {
                    if(evalMap[x][y] == step)
                    {
                        if(circle(x, y, evalMap, step + 1, map) < 1)
                        {
                            prn("blocked - quiting at " + x + ", "+y);
                        }
                    }
                }
            }
            if(evalMap[finish.x][finish.y] > 0)
            {
                prn("found path - stopping");
                return plotThePath(map, evalMap, start, finish);
            }
            step++;
        }
        prn("end on step " + step);
        return null;
    }

    private MyPath plotThePath(TheMap map, int[][] evalMap, Tile start, Tile finish)
    {
        MyPath path = new MyPath(start, finish);
        Tile current = finish;
        path.path = new ArrayList<>(evalMap[finish.x][finish.y]);
        while(current != start)
        {
            path.path.add(0,current);
            current = getNext(map, evalMap,current);
            current.path=true;
        }
        return path;
    }

    private Tile getNext(TheMap map, int[][] evalMap, Tile current)
    {
        int expVal = evalMap[current.x][current.y] - 1;
        List<Tile> nearbyTiles = new ArrayList<>(4);
        addTile(nearbyTiles, map, evalMap, current, expVal, 0, 1);
        addTile(nearbyTiles, map, evalMap, current, expVal, 1, 0);
        addTile(nearbyTiles, map, evalMap, current, expVal, -1, 0);
        addTile(nearbyTiles, map, evalMap, current, expVal, 0, -1);
        return nearbyTiles.get(0);
    }

    private void addTile(List<Tile> nearbyTiles, TheMap map, int[][] evalMap, Tile current, int expVal, int dx, int dy)
    {
        int x = current.x+dx;
        int y = current.y+dy;
        if(x>=0 && x<evalMap.length && y>=0 && y<evalMap[0].length && evalMap[x][y] == expVal){
            nearbyTiles.add(map.tile(x,y));
        }
    }

    private void drawEvalMap(int[][] evalMap)
    {
        for(int x = 0; x < evalMap.length; x++)
        {
            StringBuilder line = new StringBuilder();
            for(int y = 0; y < evalMap[0].length; y++)
            {
                int val = evalMap[x][y];
                line.append(block(val,3));
            }
            prn(line.toString());
        }
    }

    private static String block(int val, int placeholderSize)
    {
        String v = String.valueOf(val);
        return "                    ".substring(0,placeholderSize-v.length())+v;
    }

    private int circle(int x, int y, int[][] evalMap, int step, TheMap map)
    {
        return markMap(x - 1, y, evalMap, step, map)
                + markMap(x + 1, y, evalMap, step, map)
                + markMap(x, y - 1, evalMap, step, map)
                + markMap(x, y + 1, evalMap, step, map);
    }

    private int markMap(int x, int y, int[][] evalMap, int step, TheMap map)
    {
        if(x >= 0 && x < evalMap.length && y >= 0 && y < evalMap[0].length)
        {
            if(evalMap[x][y] == 0)
            {
                if(!map.tile(x, y).blocked)
                {
                    evalMap[x][y] = step;
                    return 1;
                }
            }
        }
        return 0;
    }

}
