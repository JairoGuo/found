package com.jairoguo.infra.util;

import com.jairoguo.infra.common.lock.Lock;
import java.util.function.Supplier;

/**
 * @author Jairo
 * @since 2023/5/28
 */
public class LockHandlerUtil {

  public static <T> T handler(Lock lock, String lockName, Supplier<T> processing) {

    boolean isLock = false;

    try {
      isLock = lock.tryLock(lockName);
      return processing(isLock, processing);
    } finally {
      unlock(isLock, lock, lockName);
    }
  }

  public static <T> T handler(Lock lock, String lockName, long waitTime, Supplier<T> processing) {

    boolean isLock = false;

    try {
      isLock = lock.tryLock(lockName, waitTime);
      return processing(isLock, processing);
    } finally {
      unlock(isLock, lock, lockName);
    }
  }

  public static <T> T handler(
      Lock lock, String lockName, long waitTime, long expireTime, Supplier<T> processing) {

    boolean isLock = false;

    try {
      isLock = lock.tryLock(lockName, waitTime, expireTime);
      return processing(isLock, processing);
    } finally {
      unlock(isLock, lock, lockName);
    }
  }

  private static <T> T processing(boolean isLock, Supplier<T> processing) {
    if (isLock) {
      return processing.get();
    }
    return null;
  }

  private static void unlock(boolean isLock, Lock lock, String lockName) {
    if (isLock) {
      lock.unlock(lockName);
    }
  }

  private LockHandlerUtil() {}
}
