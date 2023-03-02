package de.hf.myfinance.composite;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@SpringBootApplication
@ComponentScan("de.hf")
public class CompositeServiceApplication {
    @Value("${api.common.version}")         String apiVersion;
    @Value("${api.common.title}")           String apiTitle;
    @Value("${api.common.description}")     String apiDescription;
    @Value("${app.threadPoolSize:10}")      Integer threadPoolSize;
    @Value("${app.taskQueueSize:100}")      Integer taskQueueSize;


    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public OpenAPI getOpenApiDocumentation() {
        return new OpenAPI()
                .info(new Info().title(apiTitle)
                        .description(apiDescription)
                        .version(apiVersion));
    }

    @Bean
    public Scheduler publishEventScheduler() {
        return Schedulers.newBoundedElastic(threadPoolSize, taskQueueSize, "publish-pool");
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(CompositeServiceApplication.class, args);
    }
}
