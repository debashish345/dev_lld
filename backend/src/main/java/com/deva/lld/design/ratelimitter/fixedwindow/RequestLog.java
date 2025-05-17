package com.deva.lld.design.ratelimitter.fixedwindow;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RequestLog {
    private LocalDateTime timestamp;
    private long count;
}
