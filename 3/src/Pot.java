import java.util.concurrent.locks.ReentrantLock;

public class Pot {
    private Integer capacity;
    private volatile Integer availablePortions;
    private ReentrantLock lock;

    public Pot(int capacity) {
        lock = new ReentrantLock();
        this.capacity = capacity;
        availablePortions = 0;
    }

    public boolean canEat() {
        return availablePortions != 0;
    }

    public void eat() {
        lock.lock();
        try{
            while(!canEat()){}
            availablePortions -= 1;
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void refill() {
        availablePortions = capacity;
    }
}
