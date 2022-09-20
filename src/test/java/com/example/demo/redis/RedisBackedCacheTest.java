package com.example.demo.redis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Rule;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import redis.clients.jedis.Jedis;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration test for Redis-backed cache implementation.
 */
@Testcontainers
public class RedisBackedCacheTest {

    private RedisBackedCache underTest;

    // container {
    @Container
    public GenericContainer redis = new GenericContainer(DockerImageName.parse("redis:5.0.3-alpine"))
            .withExposedPorts(6379);

    // }

    @BeforeEach
    public void setUp() {
        String address = redis.getHost();
        Integer port = redis.getFirstMappedPort();
        Jedis jedis = new Jedis(address, port);
        String cacheName = "test";

        // Now we have an address and port for Redis, no matter where it is running
        underTest = new RedisBackedCache(jedis, cacheName);
    }

    @Test
    public void testSimplePutAndGet() {
        underTest.put("foo", "FOO");
        Optional<String> foundObject = underTest.get("foo", String.class);

        assertThat(foundObject.isPresent()).as("When an object in the cache is retrieved, it can be found").isTrue();
        assertThat(foundObject.get()).as("When we put a String in to the cache and retrieve it, the value is the same").isEqualTo("FOO");

    }
}