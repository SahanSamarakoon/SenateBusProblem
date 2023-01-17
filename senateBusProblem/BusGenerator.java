package Lab02.senateBusProblem;

import java.util.concurrent.Semaphore;

public class BusGenerator implements Runnable{

    private final Semaphore mutex;
    private final Semaphore bus;
    private final Semaphore boarded;

    public BusGenerator(Semaphore mutex, Semaphore bus, Semaphore boarded) {
        this.mutex = mutex;
        this.bus = bus;
        this.boarded = boarded;
    }

    @Override
    public void run() {
        int busCount = 1;
        while (true) {
            try {
                Bus arriveBus = new Bus(mutex, bus, boarded, busCount);
                (new Thread(arriveBus)).start();
                busCount++;
                Thread.sleep(Util.getArrivalTime(Config.BUS_ARRIVAL_MEAN_TIME));
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
