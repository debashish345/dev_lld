package com.deva.lld.design.ratelimitter.fixedwindow;

import org.springframework.web.client.HttpClientErrorException.TooManyRequests;

public interface RateLimitter {

    void access(String username) throws TooManyRequests;

}
