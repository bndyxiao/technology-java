package com.proxy;

/**
 * @author: huangzhb
 * @Date: 2019年02月01日 10:08:13
 * @Description:
 */
public class TestThread {


    public static void main(String[] args) {


        Res res = new Res();

        Thread thread1 = new Thread(new Thread1(res));
        Thread thread2 = new Thread(new Thread2(res));
        thread1.start();
        thread2.start();

    }

}
