package de.hf.myfinance.composite.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.hf.myfinance.restapi.MarketDataApi;
import de.hf.myfinance.restmodel.EndOfDayPrices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

@Component
public class MFMarketdataClient implements MarketDataApi {
    private static final Logger LOG = LoggerFactory.getLogger(MFMarketdataClient.class);
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;
    private final String serviceUrl;

    @Autowired
    public MFMarketdataClient(
            RestTemplate restTemplate,
            ObjectMapper mapper,
            @Value("${app.mfvaluation.host}") String serviceHost,
            @Value("${app.mfvaluation.port}") int servicePort) {

        this.restTemplate = restTemplate;
        this.mapper = mapper;

        serviceUrl = "http://" + serviceHost + ":" + servicePort;
    }

    @Override
    public String index() {
        return null;
    }

    @Override
    public Mono<String> loadNewMarketData() {
        return null;
    }

    @Override
    public Mono<Void> savePrices(EndOfDayPrices endOfDayPrices) {
        return null;
    }

    @Override
    public Mono<EndOfDayPrices> getEndOfDayPrices(String businesskey) {
        return null;
    }
}
