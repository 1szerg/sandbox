package com.gmail.user0abc.sandbox.complexity;

import java.util.*;

import static com.gmail.user0abc.sandbox.Util.prn;


/**
 * @author Sergii Ivanov
 */
public class Complexity
{
    private static final Integer N = 10000;
    private static final int[] tests = {100, 1000, 10000};

    public static void main(String[] arr){
        testSorters();
    }

    private static void testSorters()
    {
        List<DataGenerator> generators = new ArrayList<>();
        generators.add(new ReversedNumbers());
        generators.add(new RandomNumbers(0l));

        List<Sorter<Integer>> sorters = new ArrayList<>();
        sorters.add(new BubbleSorter<>());
        sorters.add(new InsertionSorter<>());
        sorters.add(new MergeSorter<>());
        sorters.add(new SimpleSorter<>());

        for (Sorter sorter : sorters) {
            for(DataGenerator generator: generators) {
                for (int n : tests) {
                    List<Integer> testData = generator.generateData(n);
                    long timer = System.currentTimeMillis();
                    try {
                        testData = sorter.sort(testData);
                    } catch (StackOverflowError stack) {
                        prn("StackOverflowError with " + sorter.getClass().getSimpleName() + " at it=" + sorter.getIterations());
                    }
                    long time = System.currentTimeMillis() - timer;
                    if (verifySorted(testData)) {
                        prn(sorter.getClass().getSimpleName() + "\tOK\t" + generator.getClass().getSimpleName() + "\t" + testData.size() + "\t" + time + "\tms\t" +
                                "it= \t" + sorter.getIterations() + "\t, op= \t" + sorter.getOperations());
                    } else {
                        prn(sorter.getClass().getSimpleName() + " FAILED on " + generator.getClass().getSimpleName() + " (" + testData.size() + ") in " + time + " ms " +
                                "[it= " + sorter.getIterations() + ", op= " + sorter.getOperations() + "]");
                    }
                }
            }
        }
    }

    private static boolean verifySorted(List<Integer> t)
    {
        for(int i = 1; i < t.size(); i++){
            if(t.get(i-1).compareTo(t.get(i))>0){
                return false;
            }
        }
        return true;
    }

}
