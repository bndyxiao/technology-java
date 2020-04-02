package com.proxy;

/**
 * @author: huangzhb
 * @Date: 2019年02月01日 10:07:55
 * @Description:
 */
public class Thread1 implements Runnable{


    private Res res = null;

    public Thread1(Res res) {
        this.res = res;
    }

    @Override
    public void run() {

        while(res.number <= 26) {
            synchronized (res) {
                if (res.isLetter) {
                    try {
                        res.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.print(res.number);
                    res.number++;
                    res.isLetter = true;
                    res.notify();
                }
            }
        }
    }
}
