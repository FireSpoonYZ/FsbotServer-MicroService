package org.firespoon.fsbotserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class DiceMain23337 {
    public static void main(String[] args) {
        SpringApplication.run(DiceMain23337.class, args);
    }
}
