package java8.stream;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @Description:
 * @Author: huangzhb
 * @Date: 2020/3/31 10:33
 * @Version: 1.0
 * @Copyright: (C) Copyright 2020-2034, 蓝海(福建)信息科技有限公司
 */
public class Test3 {
    public static void main(String[] args) {

    }


    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(0L, Long::sum);
    }



    public long measureSumPerf(Function<Long,Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("result: " + sum);
            if (duration < fastest) fastest = duration;
        }

        return fastest;
    }
}
