package main.java.com.project.senateBusProblem;

import java.util.concurrent.Semaphore;

public class RiderGenerator implements Runnable{

    private final Semaphore mutex;
    private final Semaphore bus;
    private final Semaphore boarded;

    public RiderGenerator(Semaphore mutex, Semaphore bus, Semaphore boarded) {
        this.mutex = mutex;
        this.bus = bus;
        this.boarded = boarded;
    }

    @Override
    public void run() {
        int riderCount = 1;

        while (true){
            try {
                Rider rider = new Rider(mutex, bus, boarded, riderCount);
                (new Thread(rider)).start();
                riderCount++;
                Thread.sleep(Util.getArrivalTime(Config.RIDER_ARRIVAL_MEAN_TIME));
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
