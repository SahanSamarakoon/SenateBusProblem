package Lab02.senateBusProblem;

public class Main {

    public static void main(String[] args){
        BusGenerator busGenerator = new BusGenerator(Config.MUTEX, Config.BUS, Config.BOARDED);
        RiderGenerator riderGenerator = new RiderGenerator(Config.MUTEX, Config.BUS, Config.BOARDED);
        (new Thread(busGenerator)).start();
        (new Thread(riderGenerator)).start();
    }
}
