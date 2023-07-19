package com.jairoguo.core.concurrent.config;

import org.redisson.Redisson;
import org.redisson.config.Config;

/**
 * @author Jairo
 * @since 2023/7/14
 */
public class RedissonConfig {
  private static final Config config = new Config();
  private static Redisson redisson = null;

  static {
    config.useSingleServer().setAddress("127.0.0.1:6379");
    redisson = (Redisson) Redisson.create(config);
  }

  public static Redisson getRedisson() {
    return redisson;
  }

  private RedissonConfig() {}
}
