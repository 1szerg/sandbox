package com.gmail.user0abc.sandbox.complexity;

import java.util.List;

/**
 * @author Sergii Ivanov
 */
public interface Sorter<T extends Comparable> {
    List<T> sort(List<T> arr);

    int getIterations();

    int getOperations();
}
