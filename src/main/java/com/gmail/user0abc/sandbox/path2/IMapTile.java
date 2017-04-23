package com.gmail.user0abc.sandbox.path2;/* $Id$
 * Created by sergii.ivanov on 4/11/2017.
 */

public interface IMapTile
{
    int getX();

    int getY();

    int getPassingCost(); // higher - more expensive to pass, -1 non-passable
}
