package com.gmail.user0abc.sandbox.complexity;

import java.util.List;

/**
 * @author Sergii Ivanov
 */
public interface DataGenerator<T> {

    List<T> generateData(int n);

}
