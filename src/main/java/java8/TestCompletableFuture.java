package java8;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * @Description:
 * @Author: huangzhb
 * @Date: 2020/3/6 9:42
 * @Version: 1.0
 * @Copyright: (C) Copyright 2020-2034, 蓝海(福建)信息科技有限公司
 */
public class TestCompletableFuture {


    public static void main(String[] args) {


        // 创建无参构造创建CompletableFuture
        CompletableFuture<String> completableFuture = new CompletableFuture<>();


        try {
            // 1.获取completableFuture结果
            // get()方法会一致阻塞直到Future完成,因此以上的调用将被永远阻塞,因为该Future一直不会完成
            //String result = completableFuture.get();
            //System.out.println(result);
            completableFuture.complete("Future's Result");


            // 2.使用runAsync()运行异步计算
            // 如果想异步的运行一个后台任务并且不想改任务返回任何东西，这时候可以使用
            /*CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable(){

                @Override
                public void run() {

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch(Exception e) {
                        throw new IllegalArgumentException(e);
                    }
                    System.out.println("I'll run in a separate thread than the main thread");
                }
            });

            future.get();*/
            /*CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (Exception e) {
                    throw new IllegalArgumentException(e);
                }
                System.out.println("I'll run in a separate thread than the main thread");
                try {
                    FileOutputStream fos = new FileOutputStream(new File("d:/a.txt"));
                    fos.write("hello".getBytes());
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });*/
           /*future.get();*/

           // 3.使用supplyAsync() 运行一个异步任务并返回结果
           // 当任务不需要返回任何东西的时候，CompletableFuture.runAsync()非常有用。
           // 但是如果你的后台任务需要返回一些结果应该要怎么样?
           // CompletableFuture.supplyAsync()就是你的选择，它持有supplier<T>并且返回CompletableFuture<T>,
            // T是通过调用传入的supplier取得的值的类型
            /*CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {

                @Override
                public String get() {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch(Exception e) {
                        throw new IllegalArgumentException(e);
                    }
                    return "Result of the asynchronous computation";
                }
            });

            String result = future.get();
            System.out.println(result);*/
            // Supplier<T> 是一个简单的函数式结果，表示supplier的结果。它有一个get()方法，该方法可以写入你的后台任务中，并且返回结果
            /*CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "Result of the asynchronous computation";
            });*/


            /**
             * 一个关于Executor 和Thread Pool笔记
             * 你可能想知道，我们知道runAsync() 和supplyAsync()方法在单独的线程中执行他们的任务。但是我们不会永远只创建一个线程。
             * CompletableFuture可以从全局的 ForkJoinPool.commonPool()获得一个线程中执行这些任务。
             * 但是你也可以创建一个线程池并传给runAsync() 和supplyAsync()方法来让他们从线程池中获取一个线程执行它们的任务。
             * CompletableFuture API 的所有方法都有两个变体-一个接受Executor作为参数，另一个不这样：
             * // Variations of runAsync() and supplyAsync() methods
             * static CompletableFuture<Void>  runAsync(Runnable runnable)
             * static CompletableFuture<Void>  runAsync(Runnable runnable, Executor executor)
             * static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
             * static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)
             */

            // 创建一个线程池，并传递给其中一个方法
            /*Executor executor = Executors.newFixedThreadPool(10);
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
               try {
                   TimeUnit.SECONDS.sleep(1);
               } catch (Exception e) {
                    throw new IllegalArgumentException(e);
               }

               return "Result of the asynchronous computation";
            }, executor);

            System.out.println(future.get());*/



            // 在CompletableFuture转换和运行
            /**
             * CompletableFuture.get()方法是阻塞的。它会一直等到Future完成并且在完成后返回结果。
             * 但是，这是我们想要的吗？对于构建异步系统，我们应该附上一个回调给CompletableFuture，当Future完成的时候，自动的获取结果。
             * 如果我们不想等待结果返回，我们可以把需要等待Future完成执行的逻辑写入到回调函数中。
             *
             * 可以使用 thenApply(), thenAccept() 和thenRun()方法附上一个回调给CompletableFuture。
             */
            // 1.thenApply()
            // 可以使用 thenApply() 处理和改变CompletableFuture的结果。
            // 持有一个Function<R,T>作为参数。Function<R,T>是一个简单的函数式接口，接受一个T类型的参数，产出一个R类型的结果。
            /*CompletableFuture<String> whatsYourNameFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
                return "Rajeev";
            });

            // Attach a callback to the Future using thenApply()
            CompletableFuture<String> greetingFuture = whatsYourNameFuture.thenApply(name -> {
               return "Hello " + name;
            });

            // Block and get the result of the future
            System.out.println(greetingFuture.get());*/
            // 你也可以通过附加一系列的thenApply()在回调方法 在CompletableFuture写一个连续的转换。
            // 这样的话，结果中的一个 thenApply方法就会传递给该系列的另外一个 thenApply方法。
            /*CompletableFuture<String> welcomeText = CompletableFuture.supplyAsync(() -> {
               try {
                   TimeUnit.SECONDS.sleep(1);
               } catch (Exception e) {
                   throw new IllegalStateException(e);
               }
               return "Rajeev";
            }).thenApply(name -> {
                return "Hello " + name;
            }).thenApply(greeting -> {
                return greeting + ", Welcome to the CalliCoder Blog";
            });

            System.out.println(welcomeText.get());*/

            // 2.thenAccept()和thenRun()
            // 如果你不想从你的回调函数中返回任何东西，仅仅想在Future完成后运行一些代码片段，你可以使用thenAccept() 和 thenRun()方法，
            // 这些方法经常在调用链的最末端的最后一个回调函数中使用。
            //CompletableFuture.thenAccept() 持有一个Consumer<T> ，
            // 返回一个CompletableFuture<Void>。它可以访问CompletableFuture的结果：
            /*CompletableFuture.supplyAsync(() -> {
               return "keke";
            }).thenAccept(product -> {
                System.out.println("got product detail from remote service " + product);
            });*/

            // 虽然thenAccept()可以访问CompletableFuture的结果，但thenRun()不能访Future的结果，
            // 它持有一个Runnable返回CompletableFuture<Void>：
            /*CompletableFuture.supplyAsync(() -> {
                // Run some computation
                return "";
            }).thenRun(() -> {
                // Computation Finished
            });*/

            // 异步回调方法的笔记
            /**
             * CompletableFuture提供的所有回调方法都有两个变体：
             * `// thenApply() variants
             * <U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
             * <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn)
             * <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)`
             * 这些异步回调变体通过在独立的线程中执行回调任务帮助你进一步执行并行计算。
             * 以下示例：
             */
            CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch(Exception e) {
                    throw new IllegalStateException(e);
                }
                return "Some Result";
            }).thenApply(result -> {

                /**
                 * Executed in the same thread where the supplyAsync() task is executed
                 * or in the main thread If the supplyAsync() task completes immediately (Remove sleep() call to verify)
                 */
                return "Processed Result";
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
