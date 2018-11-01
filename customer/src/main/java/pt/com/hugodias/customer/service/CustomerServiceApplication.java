package pt.com.hugodias.customer.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@Configuration
@EnableDiscoveryClient
@ComponentScan("pt.com.hugodias.customer")
@EnableMongoRepositories(basePackages="pt.com.hugodias.customer.repositories")
@EntityScan(basePackages="pt.com.hugodias.customer.data")
public class CustomerServiceApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

}
