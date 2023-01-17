package main.java.com.project.senateBusProblem;

public class Main {

    public static void main(String[] args) throws InterruptedException{
        BusGenerator busGenerator = new BusGenerator(Config.MUTEX, Config.BUS, Config.BOARDED);
        RiderGenerator riderGenerator = new RiderGenerator(Config.MUTEX, Config.BUS, Config.BOARDED);
        (new Thread(busGenerator)).start();
        (new Thread(riderGenerator)).start();
    }
}
