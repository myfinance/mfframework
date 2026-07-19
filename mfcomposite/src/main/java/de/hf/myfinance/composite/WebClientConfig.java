package de.hf.myfinance.composite;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClientCustomizer webClientCustomizer() {
        return webClientBuilder -> {
            ConnectionProvider connectionProvider = ConnectionProvider.builder("myfinance-conn-pool")
                    .maxConnections(500)
                    .maxIdleTime(Duration.ofSeconds(20))
                    .maxLifeTime(Duration.ofSeconds(60))
                    .pendingAcquireTimeout(Duration.ofSeconds(60))
                    .evictInBackground(Duration.ofSeconds(120))
                    .lifo()
                    .build();

            HttpClient httpClient = HttpClient.create(connectionProvider)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                    .doOnConnected(connection -> connection
                            .addHandlerLast(new ReadTimeoutHandler(30, TimeUnit.SECONDS))
                            .addHandlerLast(new WriteTimeoutHandler(30, TimeUnit.SECONDS)));

            webClientBuilder.clientConnector(new ReactorClientHttpConnector(httpClient));
        };
    }
}
