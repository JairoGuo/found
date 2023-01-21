package com.jairoguo.infra.util;

import java.util.UUID;

public class FeignUtil {

    public static final String FEIGN_ID_HEADER = "feign_id";

    public static String generateID() {
    return String.valueOf(UUID.randomUUID());
    }
    private FeignUtil() {
    }
}
