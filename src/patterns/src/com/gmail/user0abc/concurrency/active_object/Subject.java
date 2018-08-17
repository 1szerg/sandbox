package com.gmail.user0abc.concurrency.active_object;

public class Subject
{
    private double val = 0.0;

    double getVal(){
        return val;
    }

    public void doSomething() {
        val = 1.0;
        if(getVal() != 1d) {
            throw new RuntimeException("method doSomething() has been interfered by other method");
        }
    }

    public void doSomethingElse() {
        val = 2.0;
        if(getVal() != 2d){
            throw new RuntimeException("method doSomethingElse() has been interfered by other method");
        }
    }

}
