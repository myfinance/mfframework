package de.hf.myfinance.composite.api;

import de.hf.framework.exceptions.MFException;
import de.hf.framework.utils.ServiceUtil;
import de.hf.myfinance.composite.clients.MFInstrumentClient;
import de.hf.myfinance.event.Event;
import de.hf.myfinance.exception.MFMsgKey;
import de.hf.myfinance.restapi.CompositeApi;
import de.hf.myfinance.restmodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.time.LocalDate;

import static de.hf.myfinance.event.Event.Type.CREATE;

@RestController
public class CompositeApiImpl implements CompositeApi {
    ServiceUtil serviceUtil;
    MFInstrumentClient instrumentClient;
    @Value("${api.common.version}")
    String apiVersion;

    private final StreamBridge streamBridge;
    private final Scheduler publishEventScheduler;

    @Autowired
    public CompositeApiImpl(ServiceUtil serviceUtil, MFInstrumentClient instrumentClient, StreamBridge streamBridge, @Qualifier("publishEventScheduler") Scheduler publishEventScheduler) {
        this.serviceUtil = serviceUtil;
        this.instrumentClient = instrumentClient;
        this.streamBridge = streamBridge;
        this.publishEventScheduler = publishEventScheduler;
    }
    @Override
    public String index() {
        return "Hello compositeservice version:"+apiVersion;
    }

    @Override
    public Instrument helloInstrumentService() {
        try{
            return instrumentClient.getInstrument("1").block();
        } catch(MFException e) {
            throw e;
        }
        catch(Exception e) {
            throw new MFException(MFMsgKey.UNSPECIFIED, e.getMessage());
        }
    }

    @Override
    public Mono<String> saveInstrument(Instrument instrument){
        return Mono.fromCallable(() -> {

            sendMessage("validateInstrumentRequest-out-0",
                    new Event<>(CREATE, instrument.getBusinesskey(), instrument));
            return "instrument saved:"+ instrument;
        }).subscribeOn(publishEventScheduler);
    }

    @Override
    public Mono<String> saveTransaction(Transaction transaction) {
        return Mono.fromCallable(() -> {

            sendMessage("validateTransactionRequest-out-0",
                    new Event<>(CREATE, transaction.toString(), transaction));
            return "transaction saved:"+transaction;
        }).subscribeOn(publishEventScheduler);
    }

    @Override
    public Mono<String> saveRecurrentTransaction(RecurrentTransaction transaction) {
        return Mono.fromCallable(() -> {

            sendMessage("validateRecurrentTransactionRequest-out-0",
                    new Event<>(CREATE, transaction.toString(), transaction));
            return "recurrentTransaction saved:"+transaction;
        }).subscribeOn(publishEventScheduler);
    }

    @Override
    public Mono<String> processRecurrentTransaction() {
        return Mono.fromCallable(() -> {

            sendMessage("processRecurrentTransaction-out-0",
                    new Event<>(CREATE, "processRecurrentTransactions", null));
            return "process recurrent Transactions started:";
        }).subscribeOn(publishEventScheduler);
    }

    @Override
    public Mono<String> delTransaction(String transactionId) {
        return null;
    }

    @Override
    public Mono<String> loadNewMarketData() {
        return Mono.fromCallable(() -> {

            sendMessage("loadNewMarketDataProcessor-out-0",
                    new Event<>(CREATE, "load", null));
            return "MarketData loading started:";
        }).subscribeOn(publishEventScheduler);
    }


    @Override
    public Mono<EndOfDayPrices> getEndOfDayPrices(String businesskey) {
        return null;
    }

    @Override
    public ValueCurve getInstrumentValues(String tenantBusinesskey, LocalDate startDate, LocalDate endDate) {
        return null;
    }

    @Override
    public Flux<RecurrentTransaction> listRecurrentTransactions() {
        return null;
    }


    /**
     * Since the sendMessage() uses blocking code, when calling streamBridge,
     * it has to be executed on a thread provided by a dedicated scheduler, publishEventScheduler
     */
    private void sendMessage(String bindingName, Event<String, Object> event) {
        Message<Event<String, Object>> message = MessageBuilder.withPayload(event)
                .setHeader("partitionKey", event.getKey())
                .build();
        streamBridge.send(bindingName, message);
    }
}
