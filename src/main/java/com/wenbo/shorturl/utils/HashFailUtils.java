package com.wenbo.shorturl.utils;

import cn.hutool.cache.impl.LRUCache;
import com.wenbo.shorturl.modle.MapConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author changwenbo
 * @date 2024/6/17 19:35
 */
@Component
@Slf4j
public class HashFailUtils {
    private ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);

    @PostConstruct
    public void printHashFail() {
        ses.scheduleWithFixedDelay(() -> process(), 60, 10, TimeUnit.SECONDS);
    }

    private void process() {
        LRUCache<String, String> map = MapConstants.hashFailMap;
        log.info("hash冲突Map size = {}, map = {}", map.size(), GsonUtil.toJson(map));
    }
}
