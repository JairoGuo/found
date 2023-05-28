package com.jairoguo.infra.util;
/**
 * @author Jairo
 * @since 2023/5/28
 */
public class TimeConversionUtil {
    // 将纳秒转换为微秒
    public static long nanosecondsToMicroseconds(long nanoseconds) {
        return nanoseconds / 1000;
    }

    // 将纳秒转换为毫秒
    public static long nanosecondsToMilliseconds(long nanoseconds) {
        return nanoseconds / 1000000;
    }

    // 将纳秒转换为秒
    public static double nanosecondsToSeconds(long nanoseconds) {
        return nanoseconds / 1_000_000_000.0;
    }

    // 将微秒转换为纳秒
    public static long microsecondsToNanoseconds(long microseconds) {
        return microseconds * 1000;
    }

    // 将毫秒转换为纳秒
    public static long millisecondsToNanoseconds(long milliseconds) {
        return milliseconds * 1_000_000;
    }

    // 将秒转换为纳秒
    public static long secondsToNanoseconds(double seconds) {
        return (long) (seconds * 1_000_000_000);
    }

    // 将微秒转换为毫秒
    public static long microsecondsToMilliseconds(long microseconds) {
        return microseconds / 1000;
    }

    // 将微秒转换为秒
    public static double microsecondsToSeconds(long microseconds) {
        return microseconds / 1_000_000.0;
    }

    // 将毫秒转换为微秒
    public static long millisecondsToMicroseconds(long milliseconds) {
        return milliseconds * 1000;
    }

    // 将毫秒转换为秒
    public static double millisecondsToSeconds(long milliseconds) {
        return milliseconds / 1000.0;
    }

    // 将秒转换为微秒
    public static long secondsToMicroseconds(double seconds) {
        return (long) (seconds * 1_000_000);
    }

    // 将秒转换为毫秒
    public static long secondsToMilliseconds(double seconds) {
        return (long) (seconds * 1000);
    }

    private TimeConversionUtil() {
    }
}

