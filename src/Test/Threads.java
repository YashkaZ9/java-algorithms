package Test;

import java.util.Currency;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;
import java.util.concurrent.*;

public class Threads {
    public static void main(String[] args) throws InterruptedException {
//        Java multithreading

//        System.out.println("Main started...");

//        Thread t = new JThread("JThread");
//        t.start();
//        t.join();
//        Thread.sleep(1500);
//        t.interrupt();
//        Thread.sleep(1500);

//        IntStream.range(0, 6).forEach(t -> new JThread("JThread" + t).start());

//        Thread t = new Thread(new RunnableThread(), "MyThread");
//        t.start();

//        RunnableThread t = new RunnableThread();
//        new Thread(t, "Thread").start();
//        Thread.sleep(1200);
//        t.disable();
//        Thread.sleep(800);

//        CommonResource r = new CommonResource();
//        for (int i = 1; i < 5; ++i) {
//            Thread t = new Thread(new SynchronizedThread(r));
//            t.setName("Thread" + i);
//            t.start();
//        }

//        System.out.println("Main finished...");

//        StoreSynchronized store = new StoreSynchronized();
//        Supplier s = new Supplier(store);
//        Consumer c = new Consumer(store);
//        new Thread(s).start();
//        new Thread(c).start();

//        Semaphore s = new Semaphore(1);
//        CommonResource r = new CommonResource();
//        new Thread(new SemaphoreThread("Thread1", s, r)).start();
//        new Thread(new SemaphoreThread("Thread2", s, r)).start();
//        new Thread(new SemaphoreThread("Thread3", s, r)).start();

//        Semaphore s = new Semaphore(2);
//        for (int i = 0; i < 3; ++i) {
//            new Philosopher(s, i).start();
//        }

//        Exchanger<String> ex = new Exchanger<>();
//        new ExchangeThread(ex, "Hello, Java!").start();
//        new ExchangeThread(ex, "Hello, world!").start();

//        Phaser phaser = new Phaser(1);
//        new PhaserThread(phaser, "PhaserThread1").start();
//        new PhaserThread(phaser, "PhaserThread2").start();
//        int phase = phaser.getPhase();
//        phaser.arriveAndAwaitAdvance();
//        System.out.println("Фаза " + phase + " завершена");
//        phase = phaser.getPhase();
//        phaser.arriveAndAwaitAdvance();
//        System.out.println("Фаза " + phase + " завершена");
//        phase = phaser.getPhase();
//        phaser.arriveAndAwaitAdvance();
//        System.out.println("Фаза " + phase + " завершена");
//        phaser.arriveAndDeregister();

//        CommonResource cr = new CommonResource();
//        ReentrantLock lock = new ReentrantLock();
//        for (int i = 1; i < 5; ++i) {
//            new Thread(new LockedThread("Thread" + i, lock, cr)).start();
//        }

//        ReentrantLock lock = new ReentrantLock();
//        Store store = new StoreLock(lock);
//        Consumer c = new Consumer(store);
//        Supplier s = new Supplier(store);
//        new Thread(c).start();
//        new Thread(s).start();
    }
}


class LockedThread implements Runnable {
    String name;
    ReentrantLock lock;
    CommonResource r;

    public LockedThread(String name, ReentrantLock lock, CommonResource r) {
        this.name = name;
        this.lock = lock;
        this.r = r;
    }

    @Override
    public void run() {
        lock.lock();
        try {
            r.x = 1;
            for (int i = 0; i < 5; ++i) {
                System.out.println(name + ": " + r.x);
                Thread.sleep(200);
                r.x++;
            }
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        } finally {
            lock.unlock();
        }
    }
}

class PhaserThread extends Thread {
    Phaser phaser;
    String name;

    public PhaserThread(Phaser p, String n) {
        phaser = p;
        name = n;
        phaser.register();
    }

    public void run() {
        System.out.println(name + " выполняет фазу " + phaser.getPhase());
        phaser.arriveAndAwaitAdvance();
        try {
            sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " выполняет фазу " + phaser.getPhase());
        phaser.arriveAndAwaitAdvance();
        try {
            sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " выполняет фазу " + phaser.getPhase());
        phaser.arriveAndDeregister();
    }
}

class ExchangeThread extends Thread {
    Exchanger<String> ex;
    String message;

    public ExchangeThread(Exchanger<String> ex, String message) {
        this.ex = ex;
        this.message = message;
    }

    public void run() {
        try {
            message = ex.exchange(message);
            System.out.println(Thread.currentThread().getName() + " got: " + message);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

class Philosopher extends Thread {
    Semaphore semaphore;
    int num = 0;
    int id;

    public Philosopher(Semaphore s, int id) {
        semaphore = s;
        this.id = id;
    }

    public void run() {
        try {
            while (num < 3) {
                semaphore.acquire();
                System.out.println("Философ " + id + " садится трапезничать");
                sleep(500);
                num++;
                System.out.println("Философ " + id + " насытился и пошел гулять");
                semaphore.release();
                sleep(500);
            }
        } catch (InterruptedException ex) {
            System.out.println("Философу " + id + " стало плохо");
        }
    }
}

class SemaphoreThread implements Runnable {
    String name;
    Semaphore semaphore;
    CommonResource res;

    public SemaphoreThread(String name, Semaphore semaphore, CommonResource res) {
        this.name = name;
        this.semaphore = semaphore;
        this.res = res;
    }

    @Override
    public void run() {
        System.out.println(name + " запросил доступ к ресурсу");
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = 1;
        for (i = 0; i < 5; ++i)
            System.out.println(name + ": " + i);
        semaphore.release();
        System.out.println(name + " освобождает ресурс");
    }
}

interface Store {
    void put();

    void get();
}

class StoreLock implements Store {
    public int products = 0;
    ReentrantLock lock;
    Condition condition;

    public StoreLock(ReentrantLock lock) {
        this.lock = lock;
        this.condition = lock.newCondition();
    }

    @Override
    public void put() {
        lock.lock();
        try {
            while (products >= 3)
                condition.await();
            products++;
            System.out.println("Добавлен 1 товар");
            System.out.println("Товаров на складе: " + products);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public void get() {
        lock.lock();
        try {
            while (products < 1)
                condition.await();
            products--;
            System.out.println("Клиент купил 1 товар");
            System.out.println("Товаров на складе " + products);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class StoreSynchronized implements Store {
    public int products = 0;

    public synchronized void put() {
        while (products >= 3) {
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
        products++;
        System.out.println("Поставщик добавил 1 товар");
        System.out.println("Товаров на складе " + products);
        notify();
    }

    public synchronized void get() {
        while (products < 1) {
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
        products--;
        System.out.println("Потребитель купил 1 товар");
        System.out.println("Товаров на складе " + products);
        notify();
    }
}

class Supplier implements Runnable {
    Store store;

    public Supplier(Store s) {
        store = s;
    }

    public void run() {
        for (int i = 0; i < 5; ++i) {
            store.put();
        }
    }
}

class Consumer implements Runnable {
    Store store;

    public Consumer(Store s) {
        store = s;
    }

    public void run() {
        for (int i = 0; i < 5; ++i) {
            store.get();
        }
    }
}

class CommonResource {
    int x;

    public CommonResource() {
        x = 0;
    }

    public synchronized void increment() {
        x = 1;
        for (int i = 0; i < 5; ++i) {
            System.out.printf("%s: %d\n", Thread.currentThread().getName(), x);
            x++;
        }
    }
}

class SynchronizedThread implements Runnable {

    CommonResource r;

    public SynchronizedThread(CommonResource r) {
        this.r = r;
    }

    @Override
    public void run() {
//        synchronized (r) {
//            r.x = 1;
//            for (int i = 0; i < 5; ++i) {
//                System.out.printf("%s: %d\n", Thread.currentThread().getName(), r.x);
//                r.x++;
//                }
//        }
        r.increment();
    }
}

class RunnableThread implements Runnable {
    private boolean isActive;

    public void disable() {
        isActive = false;
    }

    public RunnableThread() {
        isActive = true;
    }

    public void run() {
        while (isActive) {
            System.out.printf("Thread \"%s\" started\n", Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                System.out.println("Thread \"name\" interrupted");
            }
            System.out.printf("Thread \"%s\" finished\n", Thread.currentThread().getName());
        }
    }
}

class JThread extends Thread {
    private String name;

    public JThread(String name) {
        super(name);
    }

    public void run() {
        while (!isInterrupted()) {
            System.out.printf("Thread %s started\n", Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                System.out.println("Thread \"name\" interrupted");
                System.out.println(isInterrupted());
                break;
            }
            System.out.printf("Thread %s finished\n", Thread.currentThread().getName());
        }
    }
}
