import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class MainClass {
    public static final int CARS_COUNT = 5;
    public static CyclicBarrier start = new CyclicBarrier(CARS_COUNT);
    public static CountDownLatch startCars = new CountDownLatch(CARS_COUNT);
    public static CountDownLatch finishCars = new CountDownLatch(CARS_COUNT);
    public static Semaphore tunnel = new Semaphore(CARS_COUNT / 2);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < CARS_COUNT; i++) {
            cars.add(new Car(race, 20 + new Random().nextInt(10)));
        }
        cars.forEach(car -> new Thread(car).start());
        startCars.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        finishCars.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}



