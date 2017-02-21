package com.gmail.user0abc.sandbox.path;/* {ID}
 * Created by sergii.ivanov on 2/21/2017.
 */

public class Pathfinder
{
    public static void main(String[] arg)
    {
        TheMap test1 = new PathfinderTest().runMap(15, 80, 300, new SimplePathFinder());
        printMap(test1);
    }

    private static void printMap(TheMap map)
    {

        for(int i = 0; i < map.sizeX; i++)
        {
            StringBuilder b = new StringBuilder();
            for(int j = 0; j < map.sizeY; j++){
                if(map.tile(i,j).blocked){
                    b.append("X");
                }else if(map.tile(i,j).path){
                    b.append("o");
                }else{
                    b.append(".");
                }
            }
            prn(b.toString());
        }

    }

    public static void prn(String s)
    {
        System.out.println(s);
    }

}
