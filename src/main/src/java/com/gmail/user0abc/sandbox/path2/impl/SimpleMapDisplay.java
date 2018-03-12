package com.gmail.user0abc.sandbox.path2.impl;

import com.gmail.user0abc.sandbox.path2.MapDisplay;
import com.gmail.user0abc.sandbox.path2.MapTiles;


public class SimpleMapDisplay implements MapDisplay
{
    @Override
    public void display(MapTiles map)
    {
        for(int x = 0; x < map.sizeX(); x++){
            for(int y = 0; y < map.sizeY(); y++){
                if(map.tileAt(x,y).passable()){
                    System.out.print(map.tileAt(x,y).getPassingCost());
                }else{
                    System.out.print("X");
                }
            }
            System.out.println();
        }
    }
}
