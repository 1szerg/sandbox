package com.gmail.user0abc.sandbox.evo;/* {ID}
 * Created by sergii.ivanov on 2/17/2017.
 */

import java.util.List;
import java.util.Random;

public interface DoveFactory
{
    List<Dove> generate(Dove root, int population, Random random);
}
