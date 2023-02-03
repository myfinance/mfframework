package de.hf.myfinance.composite.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.hf.myfinance.restapi.TransactionApi;
import de.hf.myfinance.restmodel.RecurrentTransaction;
import de.hf.myfinance.restmodel.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Component
public class MFTransactionClient implements TransactionApi {
    private static final Logger LOG = LoggerFactory.getLogger(MFTransactionClient.class);
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;
    private final String trasnactionServiceUrl;

    @Autowired
    public MFTransactionClient(
            RestTemplate restTemplate,
            ObjectMapper mapper,
            @Value("${app.mftransactions.host}") String transactionServiceHost,
            @Value("${app.mftransactions.port}") int transactionServicePort) {

        this.restTemplate = restTemplate;
        this.mapper = mapper;

        trasnactionServiceUrl = "http://" + transactionServiceHost + ":" + transactionServicePort;
    }

    @Override
    public String index() {
        return null;
    }

    @Override
    public Mono<String> delRecurrentTransfer(String recurrentTransactionId) {
        return null;
    }

    @Override
    public Mono<String> addRecurrentTransaction(RecurrentTransaction recurrentTransaction) {
        return null;
    }

    @Override
    public Mono<String> processRecurrentTransaction() {
        return null;
    }

    @Override
    public Mono<String> saveTransaction(Transaction transaction) {
        return null;
    }

    @Override
    public Mono<String> delTransaction(String transactionId) {
        return null;
    }

    @Override
    public Flux<Transaction> listTransactions(LocalDate startDate, LocalDate endDate) {
        return null;
    }

    @Override
    public Flux<RecurrentTransaction> listRecurrentTransactions() {
        return null;
    }
}
