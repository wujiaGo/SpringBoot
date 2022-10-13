package com.org.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Bio {

    //单例的ServerSocket
    private static ServerSocket server;

    public void test() throws IOException, InterruptedException {
        server = new ServerSocket(1111);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Socket socket = server.accept();//BIO:服务端接收到链接,新起线程等待读数据并处理数据
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    InputStream inputStream = socket.getInputStream();
                                    System.out.println("开始等待...." + "当前线程:" + Thread.currentThread().getName() + Thread.currentThread().getId());
                                    int count = 0;
                                    while (count == 0) {
                                        count = inputStream.available();
                                    }
                                    byte[] a = new byte[count];
                                    while (inputStream.read(a) > 0) {
                                        System.out.println("receive:" + new String(a) + "当前线程:" + Thread.currentThread().getName() + Thread.currentThread().getId());
                                    }
                                    inputStream.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }, "接收到消息新起线程-").start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "服务器接收线程").start();

        Socket socketClient = new Socket("127.0.0.1", 1111);
        Socket socketClient2 = new Socket("127.0.0.1", 1111);
        Thread.sleep(5000);
        new Thread(new Runnable() {//新起线程标识客户端:一秒之后客户端发送数据
            @Override
            public void run() {
                try {
                    String a = "message";
                    OutputStream outputStream = socketClient.getOutputStream();
                    System.out.println("send:" + a + "当前线程:" + Thread.currentThread().getName() + Thread.currentThread().getId());
                    outputStream.write(a.getBytes());
                    outputStream.flush();
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "客户端发送线程1-").start();
        new Thread(new Runnable() {//新起线程标识客户端:一秒之后客户端发送数据
            @Override
            public void run() {
                try {
                    String a = "message";
                    OutputStream outputStream = socketClient2.getOutputStream();
                    System.out.println("send:" + a + "当前线程:" + Thread.currentThread().getName() + Thread.currentThread().getId());
                    outputStream.write(a.getBytes());
                    outputStream.flush();
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "客户端发送线程2-").start();
    }

}