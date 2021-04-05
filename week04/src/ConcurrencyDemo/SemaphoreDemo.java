package ConcurrencyDemo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreDemo {
    // 8个worker 2个机器
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        AtomicInteger atomicInteger = new AtomicInteger(0);

        for(int i = 0; i < 8; i++) {
            new Thread(new Worker(semaphore, atomicInteger)).start();
        }
    }
}

class Worker implements Runnable {
    private Semaphore semaphore;
    private AtomicInteger atomicInteger;

    public Worker(Semaphore s, AtomicInteger a) {
        semaphore = s;
        atomicInteger = a;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            int id = atomicInteger.getAndIncrement();
            System.out.println("第" + id + "号工人正在使用机器...");
            Thread.sleep(1500);
            System.out.println("第" + id + "号工人释放机器...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }

    }
}
