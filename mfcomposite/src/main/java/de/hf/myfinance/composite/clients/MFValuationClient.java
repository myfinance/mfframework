package de.hf.myfinance.composite.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.hf.framework.exceptions.MFException;
import de.hf.myfinance.exception.MFMsgKey;
import de.hf.myfinance.restapi.ValuationApi;
import de.hf.myfinance.restmodel.Cashflow;
import de.hf.myfinance.restmodel.Instrument;
import de.hf.myfinance.restmodel.PortfolioMetrics;
import de.hf.myfinance.restmodel.Position;
import de.hf.myfinance.restmodel.ValuationType;
import de.hf.myfinance.restmodel.ValueCurve;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
    public Mono<ValueCurve> getValueCurve(String businesskey, LocalDate startDate, LocalDate endDate, ValuationType valuationType) {
        return webClient.get()
                .uri(valuationServiceUrl + "/getvaluecurve?businesskey="+businesskey+"&startDate="+startDate+"&endDate="+endDate+"&valType="+valuationType)
                .retrieve().bodyToMono(ValueCurve.class);
    }

    @Override
    public Mono<Double> getValue(String businesskey, LocalDate date, ValuationType valuationType) {
        return webClient.get()
                .uri(valuationServiceUrl + "/getvalue?businesskey="+businesskey+"&date="+date+"&valType="+valuationType)
                .retrieve().bodyToMono(Double.class);
    }


    @Override
    public Mono<LocalDateTime> getValueTs(String businesskey) {
        return webClient.get()
                .uri(valuationServiceUrl + "/getvaluets?businesskey="+businesskey)
                .retrieve().bodyToMono(LocalDateTime.class);
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
    public Flux<Position> getPositions(List<String> depotKeys) {
        

        URI uri = UriComponentsBuilder
            .fromHttpUrl(valuationServiceUrl + "/positions")
            .queryParam("depots", depotKeys.toArray()) 
            .build()
            .encode()
            .toUri();
        return webClient.get().uri(uri)
                .retrieve().bodyToFlux(Position.class);
    }

    @Override
    public String index() {
        throw new MFException(MFMsgKey.UNSPECIFIED, "not implemented yet");
    }

    @Override
    public Mono<ValueCurve> recalcAndGetValueCurve(String businesskey) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recalcAndGetValueCurve'");
    }

    @Override
    public Mono<Map<String, Double>> getLinkedValues(String businesskey, LocalDate valueDate, ValuationType valuationType) {
        return webClient.get()
                .uri(valuationServiceUrl + "/linkedvalues?businesskey="+businesskey+"&valueDate="+valueDate+"&valType="+valuationType)
                .retrieve().bodyToMono(new ParameterizedTypeReference<Map<String, Double>>() {});
    }

    @Override
    public Mono<String> recalcAllCurves() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recalcAllCurves'");
    }

    @Override
    public Flux<PortfolioMetrics> getPortfolioMetrics() {
        return webClient.get().uri(valuationServiceUrl + "/portfoliometrics").retrieve().bodyToFlux(PortfolioMetrics.class);
    }

    @Override
    public Mono<String> recalcAPortfolioMetrics() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recalcAPortfolioMetrics'");
    }


}
