package Task1;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeDisplay {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

        executorService.scheduleAtFixedRate(new SecondTask(), 1, 1, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(new FiveSecondTask(), 1, 5, TimeUnit.SECONDS);
    }
    static class SecondTask implements Runnable {
        private final long startTime;

        public SecondTask() {
            this.startTime = System.currentTimeMillis();
        }

        @Override
        public void run() {
            long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
            System.out.println("Минуло часу: " + elapsedTime + " секунд");
        }
    }

    static class FiveSecondTask implements Runnable {
        @Override
        public void run() {
            System.out.println("Минуло 5 секунд.");
        }
    }
}