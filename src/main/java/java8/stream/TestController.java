package java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.stream.Stream;

/**
 * @Description:
 * @Author: huangzhb
 * @Date: 2020/3/19 14:08
 * @Version: 1.0
 * @Copyright: (C) Copyright 2020-2034, 蓝海(福建)信息科技有限公司
 */
public class TestController {


    public static void main(String[] args) {

        /*Stream<Integer> stream = Arrays.asList(1,2,3,4,5,6).stream();
        List<Integer> numbers = stream.reduce(
                new ArrayList<Integer>(),
                (List<Integer> l, Integer e) -> {
                    l.add(e);
                    return l;
                },
                (List<Integer> l1, List<Integer> l2) -> {
                    l1.addAll(l2);
                    return l1;
                }
        );*/


        Thread t = new Thread(){
            @Override
            public void run() {
                System.out.println("=====1111");
            }
        };
        t.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("=========2222");
            }
        });

        t2.start();

        FutureTask<Integer> task = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 100;
            }
        });

        Thread t3 = new Thread(task, "task");
        t3.start();
        try {
            int result = task.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
