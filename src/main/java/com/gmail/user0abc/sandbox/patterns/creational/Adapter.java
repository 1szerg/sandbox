package com.gmail.user0abc.sandbox.patterns.creational;

import java.util.Arrays;
import java.util.List;

import static com.gmail.user0abc.sandbox.Util.prn;

/**
 * @author Sergii Ivanov
 */
public class Adapter {

    public static void main(String[] args) {
        jdkClassic();
        new Adapter().doAdapter();
    }

    private void doAdapter() {
        new Client().doCall();
    }

    class Client {
        void doCall() {
            ITarget target = new AdapterClass();
            prn(target.methodA());
        }
    }

    interface ITarget {
        String methodA();
    }

    class AdapterClass implements ITarget {
        private Adaptee adaptee = new Adaptee();

        @Override
        public String methodA() {
            StringBuilder sb = new StringBuilder();
            for(char c: adaptee.methodB()){
                sb.append(c);
            }
            return sb.toString();
        }
    }

    class Adaptee {
        public char[] methodB() {
            char[] res = {120, 121, 122};
            return res;
        }
    }

    private static void jdkClassic() {
        Integer[] arr = {1, 2, 3, 4, 5};
        List<Integer> list = Arrays.asList(arr);
        prn("list is " + list);
    }

}
