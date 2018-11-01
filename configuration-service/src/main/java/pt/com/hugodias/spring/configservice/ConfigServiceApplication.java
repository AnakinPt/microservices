package pt.com.hugodias.spring.configservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient
public class ConfigServiceApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(ConfigServiceApplication.class, args);
    }
}