package com.org.pro.sevice;

import com.org.pro.Test;

public class ThreadService extends Thread implements Test {

    public int a = 0;
    Eeee eeee;

    public ThreadService() {
        if (eeee == null) {
            eeee = new Eeee();
        }
    }

    public void add() {
        if (eeee != null) {
            eeee.add();
        } else {
            System.out.println("异常");
        }

    }

    public class Eeee {

        public void add() {
            a = 1;
        }

    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("i:" + i);
        }
    }

    @Override
    public void show() {
        System.out.println("原");
    }

}
