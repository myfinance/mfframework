package de.hf.myfinance.composite.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.hf.framework.exceptions.MFException;
import de.hf.myfinance.exception.MFMsgKey;
import de.hf.myfinance.restapi.ValuationApi;
import de.hf.myfinance.restmodel.Cashflow;
import de.hf.myfinance.restmodel.ValueCurve;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Component
public class MFValuationClient implements ValuationApi {

    private static final Logger LOG = LoggerFactory.getLogger(MFValuationClient.class);
    private final WebClient webClient;
    private final ObjectMapper mapper;
    private final String valuationServiceUrl;

    public MFValuationClient(
            WebClient.Builder webClient,
            ObjectMapper mapper,
            @Value("${app.mfvaluation.host}") String valuationServiceHost,
            @Value("${app.mfvaluation.port}") int valuationServicePort) {

        this.webClient = webClient.build();
        this.mapper = mapper;

        valuationServiceUrl = "http://" + valuationServiceHost + ":" + valuationServicePort;
    }

    @Override
    public Mono<ValueCurve> getValueCurve(String businesskey, LocalDate startDate, LocalDate endDate) {
        return webClient.get()
                .uri(valuationServiceUrl + "/getvaluecurve?businesskey="+businesskey+"&startDate="+startDate+"&endDate="+endDate)
                .retrieve().bodyToMono(ValueCurve.class);
    }

    @Override
    public Mono<Double> getValue(String businesskey, LocalDate date) {
        return webClient.get()
                .uri(valuationServiceUrl + "/getvalue?businesskey="+businesskey+"&date="+date)
                .retrieve().bodyToMono(Double.class);
    }

    @Override
    public Flux<Cashflow> listCashflows4Instrument(String businesskey, LocalDate startDate, LocalDate endDate) {
        return webClient.get().uri(valuationServiceUrl + "/cashflows4instrument?businesskey="+businesskey+"&startDate="+startDate+"&endDate="+endDate)
                .retrieve().bodyToFlux(Cashflow.class);
    }

    @Override
    public Mono<Double> getAvgExpensesOfLastYear(String businesskey) {
        return webClient.get().uri(valuationServiceUrl + "/avgexpensesoflastyear?businesskey="+businesskey)
                .retrieve().bodyToMono(Double.class);
    }

    @Override
    public String index() {
        throw new MFException(MFMsgKey.UNSPECIFIED, "not implemented yet");
    }


    private String encodeUriParameters( String parameterName, String parameterValue){

        return UriComponentsBuilder.fromPath("")
                                        .queryParam(parameterName, parameterValue)
                                        .build()
                                        .encode()
                                        .toUriString();
    }
}
