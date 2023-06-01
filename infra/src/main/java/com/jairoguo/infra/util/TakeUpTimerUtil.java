package com.jairoguo.infra.util;

import java.text.NumberFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 占用耗时计算工具类
 *
 * @author Jairo
 * @since 2023/3/20
 */
public class TakeUpTimerUtil {
  private static String lastMark = "▶︎";
  private static long lastTime = System.nanoTime();
  private static final Map<String, Long> timeMap = new LinkedHashMap<>();
  private static final Map<String, Long> timeHappenCount = new LinkedHashMap<>();

  private static final NumberFormat instance = NumberFormat.getInstance();

  public static void set(int mark) {
    set("" + mark);
  }

  public static void set(String mark) {
    long thisTime = System.nanoTime();
    String key = lastMark + " -> " + mark;
    Long lastSummary = timeMap.get(key);
    if (lastSummary == null) lastSummary = 0L;

    timeMap.put(key, System.nanoTime() - lastTime + lastSummary);
    Long lastCount = timeHappenCount.get(key);
    if (lastCount == null) lastCount = 0L;

    timeHappenCount.put(key, ++lastCount);
    lastTime = thisTime;
    lastMark = mark;
  }

  static final String FORMAT = "%30s, [总计耗时]:%20s,  [重复次数]:%20s, [平均耗时]:%20s %n";

  public static void print() {
    print(TimeUnit.NANOSECONDS);
  }

  public static void print(TimeUnit timeUnit) {

    for (Map.Entry<String, Long> entry : timeMap.entrySet()) {
      switch (timeUnit) {
        case SECONDS -> printFormat(
            entry.getKey(),
            instance.format(TimeConversionUtil.nanosecondsToSeconds(entry.getValue())),
            instance.format(
                TimeConversionUtil.nanosecondsToSeconds(timeHappenCount.get(entry.getKey()))),
            instance.format(
                TimeConversionUtil.nanosecondsToSeconds(
                    entry.getValue() / timeHappenCount.get(entry.getKey()))));
        case MILLISECONDS -> printFormat(
            entry.getKey(),
            TimeConversionUtil.nanosecondsToMilliseconds(entry.getValue()),
            TimeConversionUtil.nanosecondsToMilliseconds(timeHappenCount.get(entry.getKey())),
            TimeConversionUtil.nanosecondsToMilliseconds(
                entry.getValue() / timeHappenCount.get(entry.getKey())));
        default -> printFormat(
            entry.getKey(),
            entry.getValue(),
            timeHappenCount.get(entry.getKey()),
            entry.getValue() / timeHappenCount.get(entry.getKey()));
      }
    }
  }

  private static void printFormat(
      String mark, Object totalTimes, Object repeatTimes, Object avgTimes) {

    System.out.printf(FORMAT, mark, totalTimes, repeatTimes, avgTimes);
  }

  private TakeUpTimerUtil() {}
}
