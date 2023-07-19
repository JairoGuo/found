package com.jairoguo.core.concurrent.lock;

import com.jairoguo.infra.concurrent.lock.BaseLock;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author Jairo
 * @since 2023/7/14
 */
public class ConcurrentHashMapLock extends BaseLock {

  private static final ConcurrentHashMap<String, String> lockMap = new ConcurrentHashMap<>();
  private static final ConcurrentHashMap<String, Integer> threadMarkMap = new ConcurrentHashMap<>();
  private static final ThreadLocal<String> threadMark = new ThreadLocal<>();

  @Override
  protected boolean acquire(String lockName, long waitTime, long expireTime, TimeUnit unit) {
    synchronized (ConcurrentHashMapLock.class) {
      if (!tryAcquire(lockName, waitTime, expireTime, unit)) {
        while (!acquire(lockName, waitTime, expireTime, unit)) {
          try {
            unit.sleep(waitTime);
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
          }
        }
      }
      return true;
    }
  }

  @Override
  protected boolean tryAcquire(String lockName, long waitTime, long expireTime, TimeUnit unit) {
    synchronized (ConcurrentHashMapLock.class) {
      String lockMark = getLockMark();
      if (lockMap.containsKey(lockName)) {
        String lockedMark = lockMap.get(lockName);
        // 当天线程标记与map线程标记相同为同一把锁
        if (lockMark.equals(lockedMark)) {
          // 标记线程重入次数
          threadMarkMap.computeIfPresent(lockMark, (key, value) -> value + 1);
          return true;
        }
        return false;
      } else {
        lockMap.put(lockName, lockMark);
        threadMarkMap.putIfAbsent(lockMark, 1);
        threadMark.set(lockMark);
        return true;
      }
    }
  }

  @Override
  protected boolean release(String lockName) {
    String lockMark = getLockMark();
    if (lockMark.equals(lockMap.get(lockName))) {
      threadMarkMap.computeIfPresent(lockMark, (key, value) -> value - 1);
      if (threadMarkMap.get(lockMark) == 0) {
        threadMark.remove();
        lockMap.remove(lockName);
        threadMarkMap.remove(lockMark);
      }

    }
    return true;
  }

  private String getLockMark() {
    String threadName = Thread.currentThread().getName();
    Long threadId = Thread.currentThread().getId();
    return String.format("%s-%s", threadName, threadId);
  }
}
