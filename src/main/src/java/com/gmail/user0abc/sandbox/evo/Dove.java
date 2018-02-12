package com.gmail.user0abc.sandbox.evo;/* {ID}
 * Created by sergii.ivanov on 2/17/2017.
 */

public class Dove
{
    public float maxH, maxD, h;

    public Dove()
    {
        maxH = 0f;
        maxD = 0f;
    }

    public Dove setMaxH(float maxH)
    {
        this.maxH = maxH;
        h = maxH;
        return this;
    }

    public Dove setMaxD(float maxD)
    {
        this.maxD = maxD;
        return this;
    }

    @Override
    public String toString()
    {
        return maxH + "\t" + maxD;
    }
}
