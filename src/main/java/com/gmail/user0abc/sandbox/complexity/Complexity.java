package com.gmail.user0abc.sandbox.complexity;

import java.util.ArrayList;
import java.util.List;

import static com.gmail.user0abc.sandbox.Util.prn;


/**
 * @author Sergii Ivanov
 */
public class Complexity {
    private static final Integer N = 10000;
    private static final int[] tests = {1000, 100000, 1000000};
    private static long SORTTIMEOUT = 60000L;
    static boolean interrupted, stackOverflow;

    public static void main(String[] arr) {
        testSorters();
    }

    private static void testSorters() {
        List<DataGenerator> generators = new ArrayList<>();
        generators.add(new ReversedNumbers());
        generators.add(new PlainNumbers());
        generators.add(new RandomNumbers(0L));
        generators.add(new RandomNumbers(1000000L));

        List<Sorter<Integer>> sorters = new ArrayList<>();
        sorters.add(new BubbleSorter<>());
        sorters.add(new InsertionSorter<>());
        sorters.add(new MergeSorter<>());
        sorters.add(new SelectionSorter<>());
        sorters.add(new QuickSorter<>());
        sorters.add(new QuickSorter2<>());
        sorters.add(new HeapSorter<>());
        sorters.add(new QuickSorter2Multithreaded<>());

        for (Sorter sorter : sorters) {
            for (DataGenerator generator : generators) {
                for (int n : tests) {
                    List<Integer> testData = generator.generateData(n);
                    long timer = System.currentTimeMillis();
                    interrupted = false;
                    stackOverflow = false;
                    Thread runner = new Thread(() -> {
                        try {
                            sorter.sort(testData);
                        } catch (StackOverflowError error) {
                            stackOverflow = true;
                        }
                    });
                    runner.start();
                    Thread controller = new Thread(() -> {
                        synchronized (sorter) {
                            try {
                                sorter.wait(SORTTIMEOUT);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (runner.isAlive()) {
                                runner.stop();
                                interrupted = true;
                            }
                        }
                    });
                    controller.start();

                    try {
                        runner.join();
                    } catch (InterruptedException e) {/*it never comes here*/}
                    controller.stop();

                    if (interrupted) {
                        prn(formatOutput(sorter, generator, testData.size(), System.currentTimeMillis() - timer, "TIMEOUT"));
                        continue;
                    }
                    if (stackOverflow) {
                        prn(formatOutput(sorter, generator, testData.size(), System.currentTimeMillis() - timer, "STACKOVERFLOW"));
                        continue;
                    }

                    long time = System.currentTimeMillis() - timer;
                    if (verifySorted(testData)) {
                        prn(formatOutput(sorter, generator, testData.size(), time, "OK"));
                    } else {
                        prn(formatOutput(sorter, generator, testData.size(), time, "UNSORTED"));
                    }
                }
            }
        }
    }

    static String formatOutput(Sorter sorter, DataGenerator generator, int n, long t, String status) {
        return sorter.getClass().getSimpleName() + "\t" + status + "\t" + generator.getClass().getSimpleName() + "\t" + n + "\t" + t + "\tms\t" +
                "it= \t" + sorter.getIterations() + "\t, op= \t" + sorter.getOperations();
    }

    private static boolean verifySorted(List<Integer> t) {
        for (int i = 1; i < t.size(); i++) {
            if (t.get(i - 1).compareTo(t.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }
}
