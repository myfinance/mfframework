package de.hf.myfinance.composite.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.hf.myfinance.restapi.ValuationApi;
import de.hf.myfinance.restmodel.ValueCurve;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Component
public class MFValuationClient implements ValuationApi {

    private static final Logger LOG = LoggerFactory.getLogger(MFValuationClient.class);
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;
    private final String valuationServiceUrl;

    @Autowired
    public MFValuationClient(
            RestTemplate restTemplate,
            ObjectMapper mapper,
            @Value("${app.mfvaluation.host}") String valuationServiceHost,
            @Value("${app.mfvaluation.port}") int valuationServicePort) {

        this.restTemplate = restTemplate;
        this.mapper = mapper;

        valuationServiceUrl = "http://" + valuationServiceHost + ":" + valuationServicePort;
    }

    @Override
    public String index() {
        return null;
    }

    @Override
    public Mono<ValueCurve> getValueCurve(String businesskey, LocalDate startDate, LocalDate endDate) {
        return null;
    }

    @Override
    public Mono<Double> getValue(String businesskey, LocalDate date) {
        return null;
    }
}
