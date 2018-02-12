package com.gmail.user0abc.sandbox.path;/* {ID}
 * Created by sergii.ivanov on 2/21/2017.
 */

import static com.gmail.user0abc.sandbox.Util.prn;

public class Pathfinder
{
    public static void main(String[] arg)
    {
        Long timer = System.currentTimeMillis();
        TheMap test1 = new PathfinderTest().runMap(45, 200, 2700, new LeePathFinder());
        prn("time - " + (System.currentTimeMillis()-timer));
        printMap(test1);
    }

    private static void printMap(TheMap map)
    {

        for(int i = 0; i < map.sizeX; i++)
        {
            StringBuilder b = new StringBuilder();
            for(int j = 0; j < map.sizeY; j++){
                if(map.tile(i,j).blocked){
                    b.append("O");
                }else if(map.tile(i,j).start){
                    b.append("A");
                }else if(map.tile(i,j).finish){
                    b.append("B");
                }else if(map.tile(i,j).path){
                    b.append("x");
                }else{
                    b.append(" ");
                }
            }
            prn(b.toString());
        }

    }
}
