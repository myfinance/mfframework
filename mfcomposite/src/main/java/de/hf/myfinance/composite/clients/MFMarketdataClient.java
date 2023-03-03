package de.hf.myfinance.composite.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.hf.framework.exceptions.MFException;
import de.hf.myfinance.exception.MFMsgKey;
import de.hf.myfinance.restapi.MarketDataApi;
import de.hf.myfinance.restmodel.EndOfDayPrices;
import de.hf.myfinance.restmodel.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class MFMarketdataClient implements MarketDataApi {
    private static final Logger LOG = LoggerFactory.getLogger(MFMarketdataClient.class);

    private final ObjectMapper mapper;
    private final WebClient webClient;
    private final String serviceUrl;

    @Autowired
    public MFMarketdataClient(
            ObjectMapper mapper,
            WebClient.Builder webClient,
            @Value("${app.mfvaluation.host}") String serviceHost,
            @Value("${app.mfvaluation.port}") int servicePort) {

        this.webClient = webClient.build();
        this.mapper = mapper;

        serviceUrl = "http://" + serviceHost + ":" + servicePort;
    }

    @Override
    public Mono<EndOfDayPrices> getEndOfDayPrices(String businesskey) {
        return webClient.get().uri(serviceUrl + "/endOfDayPrices?businesskey="+businesskey)
                .retrieve().bodyToMono(EndOfDayPrices.class);
    }

    @Override
    public String index() {
        throw new MFException(MFMsgKey.UNSPECIFIED, "not implemented yet");
    }

    @Override
    public Mono<String> loadNewMarketData() {
        throw new MFException(MFMsgKey.UNSPECIFIED, "not implemented yet");
    }

    @Override
    public Mono<Void> savePrices(EndOfDayPrices endOfDayPrices) {
        throw new MFException(MFMsgKey.UNSPECIFIED, "not implemented yet");
    }
}
