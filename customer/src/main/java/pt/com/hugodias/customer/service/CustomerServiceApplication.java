package pt.com.hugodias.customer.service;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import pt.com.hugodias.customer.batch.CustomerItemProcessor;
import pt.com.hugodias.customer.dto.CustomerDto;

@SpringBootApplication
@Configuration
@EnableDiscoveryClient
@ComponentScan("pt.com.hugodias.customer")
@EnableMongoRepositories(basePackages="pt.com.hugodias.customer.repositories")
@EntityScan(basePackages="pt.com.hugodias.customer.data")
@EnableBatchProcessing
public class CustomerServiceApplication {
	@Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
    public FlatFileItemReader<CustomerDto> reader() {
        return new FlatFileItemReaderBuilder<CustomerDto>()
            .name("personItemReader")
            .resource(new ClassPathResource("sample-data.csv"))
            .delimited()
            .names(new String[]{"firstName", "lastName"})
            .fieldSetMapper(new BeanWrapperFieldSetMapper<CustomerDto>() {{
                setTargetType(CustomerDto.class);
            }})
            .build();
    }

    @Bean
    public CustomerItemProcessor processor() {
        return new CustomerItemProcessor();
    }
    
//    @Bean
//    public JpaItemWriter<Headquarters> writer() {
//        val writer = new JpaItemWriter<Headquarters>();
//        writer.setEntityManagerFactory(enti);
//    }
//    
//    @Bean
//    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
//        return jobBuilderFactory.get("importUserJob")
//            .incrementer(new RunIdIncrementer())
//            .listener(listener)
//            .flow(step1)
//            .end()
//            .build();
//    }
//
//    @Bean
//    public Step step1(JpaItemWriter<Headquarters> writer) {
//        return stepBuilderFactory.get("step1")
//            .<Headquarters, Headquarters> chunk(10)
//            .reader(reader())
//            .processor(processor())
//            .writer(writer)
//            .build();
//    }
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//
//        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
//        lef.setPackagesToScan("pt.com.hugodias.customer.repositories");
//        lef.setDataSource();
//        lef.setJpaVendorAdapter(jpaVendorAdapter());
//        lef.setJpaProperties(new Properties());
//        return lef;
//    }

}
