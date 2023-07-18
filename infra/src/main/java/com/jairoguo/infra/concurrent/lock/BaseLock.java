package com.jairoguo.infra.concurrent.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author Jairo
 * @since 2023/7/14
 */
public abstract class BaseLock implements Lock {

  @Override
  public void lock() {
    lock(Thread.currentThread().getName());
  }

  @Override
  public void lock(String lockName) {
    acquire(lockName, 0, 0, TimeUnit.SECONDS);
  }

  @Override
  public boolean tryLock(String lockName, long waitTime, long expireTime, TimeUnit unit) {

    long time = 0;
    while (!tryAcquire(lockName, waitTime, expireTime, unit)) {

      if (waitTime == 0) {
        return false;
      }

      try {
        unit.sleep(1);
        if (time++ == waitTime) {
          return false;
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        // 日志记录获取锁异常
        return false;
      }
    }
    return true;
  }

  @Override
  public void unlock() {
    unlock(Thread.currentThread().getName());
  }

  @Override
  public void unlock(String lockName) {
    release(lockName);
  }

  /**
   * 阻塞获取锁
   *
   * @param lockName 锁名称
   * @param waitTime 等待时间
   * @param expireTime 过期时间
   * @param unit 时间单位
   * @return 返回是否加锁成功
   */
  protected abstract boolean acquire(
      String lockName, long waitTime, long expireTime, TimeUnit unit);

  /**
   * 非阻塞获取锁
   *
   * @param lockName 锁名称
   * @param waitTime 等待时间
   * @param expireTime 过期时间
   * @param unit 时间单位
   * @return 返回是否加锁成功
   */
  protected abstract boolean tryAcquire(
      String lockName, long waitTime, long expireTime, TimeUnit unit);

  protected abstract boolean release(String lockName);
}
