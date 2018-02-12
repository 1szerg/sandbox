package com.gmail.user0abc.sandbox.path2.impl;

import com.gmail.user0abc.sandbox.path2.MapTile;

/*******************************************************************************
 * Copyright (c) 2015 Ericsson. All Rights Reserved.
 *     LICENSED MATERIAL - PROPERTY OF ERICSSON
 *     Possession and/or use of this material is subject to the provisions
 *     of a written license agreement with Ericsson
 * ERICSSON CONFIDENTIAL - RESTRICTED ACCESS
 *     This document and the confidential information it contains shall be distributed,
 *     routed or made available solely to authorized persons having a need to know
 *     within Ericsson, except with written permission of Ericsson.
 *******************************************************************************/

public class SimpleMapTile implements MapTile
{
    private int x;
    private int y;
    private int passCost;
    private boolean passable;

    @Override
    public int getX()
    {
        return x;
    }

    @Override
    public int getY()
    {
        return y;
    }

    @Override
    public int getPassingCost()
    {
        return passCost;
    }

    @Override
    public boolean passable()
    {
        return passable;
    }
}
