package com.proxy;

import javax.annotation.security.RunAs;

/**
 * @author: huangzhb
 * @Date: 2019年02月01日 10:08:04
 * @Description:
 */
public class Thread2 implements Runnable {

    private Res res = null;


    public Thread2(Res res) {
        this.res = res;
    }

    @Override
    public void run() {
        while(res.letter <= 'Z') {
            synchronized (res) {
                if (!res.isLetter) {
                    try {
                        res.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    System.out.print(res.letter);
                    res.letter++;
                    res.isLetter=false;
                    res.notify();
                }
            }
        }
    }
}
