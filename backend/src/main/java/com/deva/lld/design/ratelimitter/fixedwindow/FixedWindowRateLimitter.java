package com.deva.lld.design.ratelimitter.fixedwindow;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class FixedWindowRateLimitter implements RateLimitter{

    private Map<String, RequestLog> userReqMap;
    private final long THRESHOLD;
    private final long WINDOW_SIZE_IN_SEC;

    public FixedWindowRateLimitter(long threshold, long windowSizeInSec) {
        THRESHOLD = threshold;
        WINDOW_SIZE_IN_SEC = windowSizeInSec;
        userReqMap = new HashMap<>();
    }

    @Override
    public void access(String username) throws HttpClientErrorException.TooManyRequests {
        RequestLog log = RequestLog.builder()
                .count(1)
                .timestamp(LocalDateTime.now())
                .build();
        if (!userReqMap.containsKey(username)) {
            userReqMap.put(username, log);
        } else {
            if (userReqMap.get(username).getTimestamp().isBefore(LocalDateTime.now().minusSeconds(WINDOW_SIZE_IN_SEC))) {
                userReqMap.put(username, log);
            } else if (userReqMap.get(username).getCount() >= THRESHOLD) {
                throw new HttpClientErrorException(HttpStatus.TOO_MANY_REQUESTS);
            } else {
                long currentCount = userReqMap.get(username).getCount();
                userReqMap.get(username).setCount(currentCount + 1);
            }
        }
    }
}
