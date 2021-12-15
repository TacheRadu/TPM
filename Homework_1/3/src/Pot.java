import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Pot {
    private Integer capacity;
    private volatile Integer availablePortions;
    private ReentrantLock lock;
    private List<Long> threadIds;
    private boolean b;

    public Pot(int capacity, boolean b) {
        lock = new ReentrantLock();
        threadIds = new LinkedList<>();
        availablePortions = 0;
        this.b = b;
        this.capacity = capacity;
    }

    public boolean canEat() {
        return availablePortions != 0;
    }

    public void eat() {
        lock.lock();
        try {
            if (b) {
                threadIds.add(Thread.currentThread().getId());
                while (threadIds.get(0) != Thread.currentThread().getId()) {
                    lock.notifyAll();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            while (!canEat()) {
            }
            availablePortions -= 1;
        } finally {
            if (b)
                threadIds.remove(0);
            lock.unlock();
        }
    }

    public void refill() {
        availablePortions = capacity;
    }
}
