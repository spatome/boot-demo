package com.spatome.boot.common.config.memcached;

import java.net.InetSocketAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.spy.memcached.MemcachedClient;

@Component
@Order(1)
@Slf4j
public class MemcachedUtil implements CommandLineRunner {

    @Autowired
    private MemcachedConfig memcachedConfig;

    private MemcachedClient memcachedClient = null;

	@Override
	public void run(String... args) throws Exception {
		try {
			memcachedClient = new MemcachedClient(new InetSocketAddress(memcachedConfig.getIp(), memcachedConfig.getPort()));
		} catch (Exception e) {
			log.error("memcachedClient创建失败", e);
		}
	}

	public MemcachedClient getMemcachedClient() {
		return memcachedClient;
	}

}
