package com.jairoguo.infra.concurrent.lock;

import java.util.concurrent.TimeUnit;

/**
 * 锁功能接口
 *
 *
 * @author Jairo
 */
public interface Lock {

  long EXPIRE_TIME = 60;

  /**
   * 阻塞加锁
   */
  void lock();


  /**
   * 通过名称阻塞加锁
   * @param lockName
   */
  void lock(String lockName);

  default boolean tryLock(String lockName) {
    return tryLock(lockName, 0);
  }

  default boolean tryLock(String lockName, long waitTime) {
    return tryLock(lockName, waitTime, EXPIRE_TIME);
  }

  default boolean tryLock(String lockName, long waitTime, long expireTime) {
    return tryLock(lockName, waitTime, expireTime, TimeUnit.SECONDS);
  }

  default boolean tryLock(String lockName, long waitTime, TimeUnit timeUnit) {
    return tryLock(lockName, waitTime, EXPIRE_TIME, timeUnit);
  }

  /**
   * 非阻塞加锁
   */
  boolean tryLock(String lockName, long waitTime, long expireTime, TimeUnit unit);

  void unlock();

  void unlock(String lockName);
}
