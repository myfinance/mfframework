package de.hf.myfinance.composite.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.hf.framework.exceptions.MFException;
import de.hf.myfinance.exception.MFMsgKey;
import de.hf.myfinance.restapi.ValuationApi;
import de.hf.myfinance.restmodel.Cashflow;
import de.hf.myfinance.restmodel.Position;
import de.hf.myfinance.restmodel.ValueCurve;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Component
public class MFValuationClient implements ValuationApi {

    private final WebClient webClient;
    private final String valuationServiceUrl;

    public MFValuationClient(
            WebClient.Builder webClient,
            ObjectMapper mapper,
            @Value("${app.mfvaluation.host}") String valuationServiceHost,
            @Value("${app.mfvaluation.port}") int valuationServicePort) {

        this.webClient = webClient.build();

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

    @Override
    public Flux<Position> getPositions() {
        return webClient.get().uri(valuationServiceUrl + "/positions")
                .retrieve().bodyToFlux(Position.class);
    }
}
