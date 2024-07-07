package Task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

    public class FizzBuzzMultiThreaded {

        public static void main(String[] args) {
            int n = 15; // Задаємо значення n
            BlockingQueue<String> queue = new LinkedBlockingQueue<>();
            AtomicInteger number = new AtomicInteger(1);

            Thread threadA = new Thread(new FizzTask(n, number, queue));
            Thread threadB = new Thread(new BuzzTask(n, number, queue));
            Thread threadC = new Thread(new FizzBuzzTask(n, number, queue));
            Thread threadD = new Thread(new NumberTask(n, number, queue));

            threadA.start();
            threadB.start();
            threadC.start();
            threadD.start();
        }

        static class FizzTask implements Runnable {
            private final int n;
            private final AtomicInteger number;
            private final BlockingQueue<String> queue;

            public FizzTask(int n, AtomicInteger number, BlockingQueue<String> queue) {
                this.n = n;
                this.number = number;
                this.queue = queue;
            }

            @Override
            public void run() {
                while (true) {
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
        }

        static class BuzzTask implements Runnable {
            private final int n;
            private final AtomicInteger number;
            private final BlockingQueue<String> queue;

            public BuzzTask(int n, AtomicInteger number, BlockingQueue<String> queue) {
                this.n = n;
                this.number = number;
                this.queue = queue;
            }

            @Override
            public void run() {
                while (true) {
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
        }

        static class FizzBuzzTask implements Runnable {
            private final int n;
            private final AtomicInteger number;
            private final BlockingQueue<String> queue;

            public FizzBuzzTask(int n, AtomicInteger number, BlockingQueue<String> queue) {
                this.n = n;
                this.number = number;
                this.queue = queue;
            }

            @Override
            public void run() {
                while (true) {
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
        }

        static class NumberTask implements Runnable {
            private final int n;
            private final AtomicInteger number;
            private final BlockingQueue<String> queue;

            public NumberTask(int n, AtomicInteger number, BlockingQueue<String> queue) {
                this.n = n;
                this.number = number;
                this.queue = queue;
            }

            @Override
            public void run() {
                int count = 1;
                while (count <= n) {
                    try {
                        String result = queue.poll();
                        if (result != null) {
                            System.out.print(result + ", ");
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
            }
        }
    }
