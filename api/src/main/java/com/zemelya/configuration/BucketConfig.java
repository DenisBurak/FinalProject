package com.zemelya.configuration;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class BucketConfig {

    @Bean
    public Bucket bucket(){
        Bandwidth limit = Bandwidth.classic(1, Refill.greedy(1, Duration.ofSeconds(1)));
        return Bucket.builder().addLimit(limit).build();
    }
}
