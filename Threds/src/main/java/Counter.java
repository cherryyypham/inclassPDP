import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Counter {
    private int value = 0;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void increment(int amount) {
        lock.writeLock().lock();
        try {
            value += amount;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int getValue() {
        lock.readLock().lock();
        try {
            return value;
        }  finally {
            lock.readLock().unlock();
        }
    }
}
