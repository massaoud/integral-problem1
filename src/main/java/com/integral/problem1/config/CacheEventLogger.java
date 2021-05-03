package com.integral.problem1.config;

import lombok.extern.slf4j.Slf4j;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

@Slf4j
public class CacheEventLogger  implements CacheEventListener<Object, Object> {

    // ...

    @Override
    public void onEvent(CacheEvent<? extends Object, ? extends Object> cacheEvent) {
        log.info(cacheEvent.getKey().toString(), cacheEvent.getOldValue(), cacheEvent.getNewValue());
    }

}