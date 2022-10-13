package com.org.pojo;

import com.org.pro.Test;
import com.org.pro.sevice.ThreadService;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest implements Comparable, Callable<Integer>, Test {

    public int count = 10;
    public int index = 0;
    public AtomicInteger a = new AtomicInteger();
    Lock reentrantLock = new ReentrantLock();
    final int threadNumber = 10;
    public final CountDownLatch countDownLatch = new CountDownLatch(threadNumber);
    ThreadService threadService;

    public ThreadTest(ThreadService threadService) {
        this.threadService = threadService;
    }

    public ThreadTest(int id) {
        this.id = id;
    }

    public ThreadTest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;


    @Override
    public int compareTo(Object o) {
        ThreadTest user = null;
        if (o instanceof ThreadTest) {
            user = (ThreadTest) o;
        } else {
            return -1;
        }
        int flag = this.id - user.id;
        return flag;
    }


    @Override
    public Integer call() throws Exception {
        System.out.println("子线程进来第" + indexUp());
        countDown();
        for (int i = 0; i < 10000; i++) {
            addA();
        }
        countDownLatch.countDown();
        return 1;
    }

    public int indexUp() throws InterruptedException {
        if (reentrantLock.tryLock(5, TimeUnit.SECONDS)) {
            try {
                return index += 1;
            } finally {
                reentrantLock.unlock();

            }
        }
        return 0;
    }

    public void countDown() throws InterruptedException {
        if (reentrantLock.tryLock(5, TimeUnit.SECONDS)) {
            try {
                count -= 1;
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public void addA() throws InterruptedException {
        if (reentrantLock.tryLock(5, TimeUnit.SECONDS)) {
            try {
                a.addAndGet(1);
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    @Override
    public void show() {
        System.out.println("代");
        threadService.show();
    }

}
