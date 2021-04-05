package Homework;

import java.util.Optional;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 * <p>
 * 一个简单的代码参考：
 */
public class Homework03 {
    // 欲求的斐波那契数列的项数
    public static int numOfTerm = 50;

    public static void main(String[] args) throws InterruptedException, ExecutionException {



        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        // 确保  拿到result 并输出
        long start = System.currentTimeMillis();

        System.out.println("======== 方法1: 开一个线程 Runable ========");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        MyRunable task1 = new MyRunable(numOfTerm, countDownLatch);
        Thread t1 = new Thread(task1);
        t1.start();
        countDownLatch.await();
        System.out.println("异步计算结果为：" + task1.getResult());
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");


        System.out.println("======== 方法2: 开多个线程 Runable ========");
        CountDownLatch countDownLatch2 = new CountDownLatch(5);
        MyRunable task2 = new MyRunable(numOfTerm, countDownLatch2);
        start = System.currentTimeMillis();
        for(int i = 0; i < 5; i++) {
            new Thread(task2).start();
        }
        countDownLatch2.await();
        System.out.println("异步计算结果为：" + task2.getResult());
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");


        System.out.println("======== 方法3: 开线程池 Callable ========");
        MyCallable task3 = new MyCallable(numOfTerm);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        FutureTask<Long> futureTask = new FutureTask<>(task3);
        start = System.currentTimeMillis();
        for(int i = 0;  i < 5; i++) {
            executorService.submit(futureTask);
        }
        executorService.shutdown();
        System.out.println("异步计算结果为：" + futureTask.get());
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        System.out.println("======== 方法4: Stream 串行 ========");
        start = System.currentTimeMillis();
        Optional<Long> result = Stream.generate(new Supplier<Long>() {
            private long a = 1;
            private long b = 1;

            @Override
            public Long get() {
                long res = a + b;
                a = b;
                b = res;
                return res;
            }
        }).limit(numOfTerm - 1).max(Long::compare);

        System.out.println("异步计算结果为：" + (result.isPresent() ? result.get() : -1));
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
    }
}

class MyRunable implements Runnable {

    private long result = 1;
    private long a = 1;
    private long b = 1;
    private int N;
    private CountDownLatch countDownLatch;

    public MyRunable(int num, CountDownLatch count) {
        this.N = num;
        this.countDownLatch = count;
    }

    public long getResult() {
        return this.result;
    }

    @Override
    public void run() {
        if(this.N < 2) {
            countDownLatch.countDown();
            return;
        }

        while(this.N >= 2) {
            result = a + b;
            a = b;
            b = result;
            this.N--;
        }
        countDownLatch.countDown();
    }
}

class MyCallable implements Callable<Long> {

    private long a = 1;
    private long b = 1;
    private int N;

    public MyCallable(int num) {
        this.N = num;
    }

    @Override
    public Long call() {
        if(this.N < 2) {
            return 1L;
        }

        long result = 0L;

        while(this.N >= 2) {
            result = a + b;
            a = b;
            b = result;
            this.N--;
        }
        return result;
    }
}

