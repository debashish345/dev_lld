package com.deva.lld.design.ratelimitter.fixedwindow;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

public class SlidingWindowRateLimitter implements RateLimitter {

    private Map<String, LinkedList<RequestLog>> requestMap;
    private final long THRESHOLD;
    private final long WINDOW_SIZE_IN_SEC;

    public SlidingWindowRateLimitter(long threshold, long windowSizeInSec) {
        THRESHOLD = threshold;
        WINDOW_SIZE_IN_SEC = windowSizeInSec;
        this.requestMap = new HashMap<>();
    }

    @Override
    public void access(String username) throws HttpClientErrorException.TooManyRequests {
        LinkedList<RequestLog> userReqLog = requestMap.get(username);

        if (Objects.isNull(userReqLog) || userReqLog.isEmpty()) {
            LinkedList<RequestLog> logs = new LinkedList<>();
            logs.addLast(new RequestLog(LocalDateTime.now()));
            requestMap.put(username, logs);
        } else {
            // remove expired request
            while (!userReqLog.isEmpty() && userReqLog.peekFirst().getTimestamp().isBefore(LocalDateTime.now().minusSeconds(WINDOW_SIZE_IN_SEC))) {
                userReqLog.removeFirst();
            }
            if (userReqLog.size() >= THRESHOLD) {
                throw new HttpClientErrorException(HttpStatus.TOO_MANY_REQUESTS);
            }
            userReqLog.addLast(new RequestLog(LocalDateTime.now()));
            requestMap.put(username, userReqLog);
        }
    }
}
