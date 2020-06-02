package components;

import java.io.File;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * Moked class- ReadWriteLock design pattern
 * @author Yehonatan Hen-207630112
 *  @author Rotem Librati-307903732
 */
public class Moked {
    Object data;
    private static volatile boolean cacheValid;
    private static final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private static File file;

    /**
     * Write a report when function called
     */
    public static void WriteReport(Vehicle vehicle) {
        rwl.readLock().lock();
        if (!cacheValid) {
            // Must release read lock before acquiring write lock
            rwl.readLock().unlock();
            rwl.writeLock().lock();
            try {
                // Recheck state because another thread might have
                // acquired write lock and changed state before we did.
                if (!cacheValid) {
                   // data = ...
                    cacheValid = true;
                }
                // Downgrade by acquiring read lock before releasing write lock
                rwl.readLock().lock();
            } finally {
                rwl.writeLock().unlock(); // Unlock write, still hold read
            }
        }

        try {
            //use(data);
        } finally {
            rwl.readLock().unlock();
        }
    }
}
