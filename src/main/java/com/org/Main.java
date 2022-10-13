package com.org;

import com.org.pojo.Flow;
import com.org.pojo.ThreadTest;
import com.org.pool.ProxyFactory;
import com.org.pro.Test;
import com.org.pro.sevice.ThreadService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication()
@MapperScan("com.org.mapper")
@EnableScheduling
public class Main {

    public static ConfigurableApplicationContext ac;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


    public void test(String[] args) throws InterruptedException, ExecutionException, IOException {
        Main testGo = new Main();
        //  testGo.testScheduledExecutor();
        //  testGo.testTreeMap();
        //  Collections.synchronizedList(new ArrayList<>());
 /*       ThreadService Flow = new ThreadService();
        System.out.println(Flow.a);
        Flow.add();内部类测试
        System.out.println(Flow.a);*/


    }


    public void treeSetTest() {
        TreeSet treeSet = new TreeSet();
        ThreadTest threadTest1 = new ThreadTest(9);
        ThreadTest threadTest2 = new ThreadTest(4);
        ThreadTest threadTest3 = new ThreadTest(7);
        treeSet.add(threadTest1);
        treeSet.add(threadTest2);
        treeSet.add(threadTest3);
        for (Object a : treeSet) {
            Flow flow = (Flow) a;
            System.out.println(flow.toString());
        }
    }

    public void treeMapTest() {
        Map<Integer, ThreadTest> treeMap = new TreeMap(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
        ThreadTest threadTest1 = new ThreadTest(9);
        ThreadTest threadTest2 = new ThreadTest(4);
        ThreadTest threadTest3 = new ThreadTest(7);
        treeMap.put(threadTest1.getId(), threadTest1);
        treeMap.put(threadTest2.getId(), threadTest2);
        treeMap.put(threadTest3.getId(), threadTest3);
        for (Map.Entry<Integer, ThreadTest> entry : treeMap.entrySet()) {
            Integer k = entry.getKey();
            ThreadTest v = entry.getValue();
            System.out.println(v.toString());
        }
    }

    public Object callTest() throws ExecutionException, InterruptedException {
        ThreadTest threadTest = new ThreadTest();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        return executorService.submit(threadTest).get();
    }

    public void countDownLatchOrThreadTest() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("i:" + i);
                countDownLatch.countDown();
            }
        });
        thread.start();
        countDownLatch.await();
        System.out.println("i:");
    }

    public void countDownLatchOrExecutorTest() {
        ExecutorService exector = Executors.newFixedThreadPool(5);
        ThreadTest threadTest = new ThreadTest();
        for (int i = 0; i < 10; i++) {
            exector.submit(threadTest);
        }
        System.out.println("主线程");
        try {
            threadTest.countDownLatch.await(500, TimeUnit.MILLISECONDS);//保证之前的所有的线程都执行完成，才会走下面的
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("超时");
        }
//        int index = 0;
//        while (Flow.count != 0 && Flow.a != 100000) {
//            try {
//                Thread.currentThread().sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            index += 1;
//            if (index > 10) {
//                System.out.println("超时" + Flow.a);
//                return;
//            }
//        }
        System.out.println("主线程结束" + threadTest.a + ",跑完了" + (int) (10 - threadTest.countDownLatch.getCount()));
    }

    public void threadTest() {
        ThreadService threadService = new ThreadService();
        threadService.start();
    }

    public void cachedThreadPoolTest() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(() -> {
            for (int a = 0; a > 10; a++) {
                final int index = a;
                System.out.println(a);
            }

        });
    }

    public void scheduledExecutorTest() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        AtomicInteger index = new AtomicInteger();
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            index.getAndIncrement();
            System.out.println("第" + index);
        }, 10, 3, TimeUnit.SECONDS);
    }

    public void jDKorCGilbTest() {
        //  new Flow(new ThreadService()).show();//静态代理测试
        //创建被代理的实例对象
        ThreadService threadService = new ThreadService();
        //创建InvocationHandler对象
        InvocationHandler renterHandler = new ProxyFactory<>(threadService);
        //创建代理对象,代理对象的每个执行方法都会替换执行Invocation中的invoke方法
        ((Test) Proxy.newProxyInstance(
                Test.class.getClassLoader(),
                new Class<?>[]{Test.class},
                renterHandler)).show();//动态代理 基于invocationGandler接口实现

        ((ThreadService) new ProxyFactory(threadService).getProxyInstance()).add();//CGlib代理,

    }


}
