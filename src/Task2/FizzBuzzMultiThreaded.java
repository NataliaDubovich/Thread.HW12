package Task2;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class FizzBuzzMultiThreaded {

    public static void main(String[] args) {
        int n = 20;
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        AtomicInteger number = new AtomicInteger(2);

        Thread threadA = new Thread(() -> {
            while (true) {
                synchronized (queue) {
                    int current = number.get();
                    if (current > n) break;
                    if (current % 3 == 0 && current % 5 != 0) {
                        try {
                            queue.put("fizz");
                            number.incrementAndGet();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });

        Thread threadB = new Thread(() -> {
            while (true) {
                synchronized (queue) {
                    int current = number.get();
                    if (current > n) break;
                    if (current % 5 == 0 && current % 3 != 0) {
                        try {
                            queue.put("buzz");
                            number.incrementAndGet();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });

        Thread threadC = new Thread(() -> {
            while (true) {
                synchronized (queue) {
                    int current = number.get();
                    if (current > n) break;
                    if (current % 3 == 0 && current % 5 == 0) {
                        try {
                            queue.put("fizzbuzz");
                            number.incrementAndGet();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });

        Thread threadD = new Thread(() -> {
            int count = 1;
            while (count <= n) {
                try {
                    String result = queue.poll();
                    if (result != null) {
                        System.out.print(result + ", ");
                        count++;
                    } else {
                        System.out.print(count + ", ");
                    }
                    count++;
                    number.incrementAndGet();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println();
        });

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

        try {
            threadA.join();
            threadB.join();
            threadC.join();
            threadD.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}