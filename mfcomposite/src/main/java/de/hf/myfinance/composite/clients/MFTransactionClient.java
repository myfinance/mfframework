package de.hf.myfinance.composite.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.hf.framework.exceptions.MFException;
import de.hf.myfinance.exception.MFMsgKey;
import de.hf.myfinance.restapi.TransactionApi;
import de.hf.myfinance.restmodel.Instrument;
import de.hf.myfinance.restmodel.RecurrentTransaction;
import de.hf.myfinance.restmodel.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Component
public class MFTransactionClient implements TransactionApi {
    private static final Logger LOG = LoggerFactory.getLogger(MFTransactionClient.class);
    private final ObjectMapper mapper;
    private final WebClient webClient;
    private final String transactionServiceUrl;

    @Autowired
    public MFTransactionClient(
            RestTemplate restTemplate,
            ObjectMapper mapper,
            WebClient.Builder webClient,
            @Value("${app.mftransactions.host}") String transactionServiceHost,
            @Value("${app.mftransactions.port}") int transactionServicePort) {

        this.webClient = webClient.build();
        this.mapper = mapper;

        transactionServiceUrl = "http://" + transactionServiceHost + ":" + transactionServicePort;
    }

    @Override
    public Flux<Transaction> listTransactions(LocalDate startDate, LocalDate endDate) {
        return webClient.get().uri(transactionServiceUrl + "/transactions?startDate="+startDate+"&endDate="+endDate)
                .retrieve().bodyToFlux(Transaction.class);
    }

    @Override
    public Flux<RecurrentTransaction> listRecurrentTransactions() {
        return webClient.get().uri(transactionServiceUrl + "/recurrenttransactions")
                .retrieve().bodyToFlux(RecurrentTransaction.class);
    }

    @Override
    public String index() {
        throw new MFException(MFMsgKey.UNSPECIFIED, "not implemented yet");
    }

    @Override
    public Mono<String> delRecurrentTransfer(String recurrentTransactionId) {
        throw new MFException(MFMsgKey.UNSPECIFIED, "not implemented yet");
    }

    @Override
    public Mono<String> saveRecurrentTransaction(RecurrentTransaction recurrentTransaction) {
        throw new MFException(MFMsgKey.UNSPECIFIED, "not implemented yet");
    }

    @Override
    public Mono<String> processRecurrentTransaction() {
        throw new MFException(MFMsgKey.UNSPECIFIED, "not implemented yet");
    }

    @Override
    public Mono<String> saveTransaction(Transaction transaction) {
        throw new MFException(MFMsgKey.UNSPECIFIED, "not implemented yet");
    }

    @Override
    public Mono<String> delTransaction(String transactionId) {
        throw new MFException(MFMsgKey.UNSPECIFIED, "not implemented yet");
    }


}
