package com.gmail.user0abc.sandbox.evo;/* {ID}
 * Created by sergii.ivanov on 2/17/2017.
 */

import java.util.Random;

public interface GenRunner
{
    GenReport runAndReport(DoveFactory doveFactory, Dove root, Random random, int population);

    Dove winner();
}
