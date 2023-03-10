package Lab02.senateBusProblem;

import java.util.concurrent.Semaphore;

public class Bus implements Runnable{

    private int n;
    private final Semaphore mutex;
    private final Semaphore bus;
    private final Semaphore boarded;
    private int count;

    public Bus(Semaphore mutex, Semaphore bus, Semaphore boarded, int count) {
        this.mutex = mutex;
        this.bus = bus;
        this.boarded = boarded;
        this.count = count;
    }

    @Override
    public void run() {
        try {
            mutex.acquire();
            System.out.println(String.format("Bus-%d arrived", count));
            n = Math.min(Config.getWaiting(), Config.MAX_BUS_CAPACITY);
            for (int i=0; i<n; i++) {
                bus.release();
                boarded.acquire();
            }
            Config.setWaiting(Math.max(Config.getWaiting()-Config.MAX_BUS_CAPACITY, 0));
            System.out.println(String.format("Bus-%d departed with %d number of riders", count, n));
            System.out.println(String.format("%d riders are waiting at the bus stop", Config.getWaiting()));
            mutex.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
