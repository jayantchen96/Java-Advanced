package ConcurrencyDemo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 10个人相约奶茶店，人到齐后出发大保健
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
        for(int i = 0; i < 10; i++) {
            new Thread(new Person(i, cyclicBarrier)).start();
        }
    }
}

class Person implements Runnable{

    private CyclicBarrier cyclicBarrier;
    private int id;

    public Person(int id, CyclicBarrier cyclicBarrier) {
        this.id = id;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10);
            System.out.println("伞兵" + id + "号卢本伟准备就绪...");
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }finally {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("出发");
        }

    }
}
