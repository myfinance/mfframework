package de.hf.myfinance.composite.api;

import de.hf.framework.exceptions.MFException;
import de.hf.framework.utils.ServiceUtil;
import de.hf.myfinance.composite.clients.MFInstrumentClient;
import de.hf.myfinance.composite.clients.MFMarketdataClient;
import de.hf.myfinance.composite.clients.MFTransactionClient;
import de.hf.myfinance.composite.clients.MFValuationClient;
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

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;

import static de.hf.myfinance.event.Event.Type.*;

@RestController
public class CompositeApiImpl implements CompositeApi {
    ServiceUtil serviceUtil;
    MFInstrumentClient instrumentClient;
    MFTransactionClient transactionClient;
    MFMarketdataClient marketdataClient;
    MFValuationClient valuationClient;
    @Value("${api.common.version}")
    String apiVersion;

    private final StreamBridge streamBridge;
    private final Scheduler publishEventScheduler;

    @Autowired
    public CompositeApiImpl(ServiceUtil serviceUtil,
                            MFInstrumentClient instrumentClient,
                            MFTransactionClient transactionClient,
                            MFMarketdataClient marketdataClient,
                            MFValuationClient valuationClient,
                            StreamBridge streamBridge,
                            @Qualifier("publishEventScheduler") Scheduler publishEventScheduler) {
        this.serviceUtil = serviceUtil;
        this.instrumentClient = instrumentClient;
        this.transactionClient = transactionClient;
        this.marketdataClient = marketdataClient;
        this.valuationClient = valuationClient;
        this.streamBridge = streamBridge;
        this.publishEventScheduler = publishEventScheduler;
    }
    @Override
    public String index() {
        return "{Hello compositeservice version:"+apiVersion + "}";
    }

    @Override
    public Principal user(Principal user) {
        return user;
    }

    /** Instruments: **/

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
            return "{\"success\": \"Tenant"+instrument.getDescription() +" saved\"}";
        }).subscribeOn(publishEventScheduler);
    }

    @Override
    public Flux<Instrument> listInstruments() {
        return instrumentClient.listInstruments();
    }

    @Override
    public Flux<Instrument> listInstrumentsForTenant(String tenantbusinesskey) {
        return instrumentClient.listInstrumentsForTenant(tenantbusinesskey);
    }

    @Override
    public Flux<Instrument> listActiveInstrumentsForTenant(String tenantbusinesskey) {
        return instrumentClient.listActiveInstrumentsForTenant(tenantbusinesskey);
    }

    @Override
    public Flux<Instrument> listInstrumentsByType(String tenantbusinesskey, InstrumentType instrumentType) {
        return instrumentClient.listInstrumentsByType(tenantbusinesskey, instrumentType);
    }

    @Override
    public Flux<Instrument> listTenants() {
        /*var tenantlist = new ArrayList<Instrument>();
        var instrument = new Instrument();
        instrument.setActive(true);
        instrument.setDescription("test1");
        instrument.setInstrumentType(InstrumentType.TENANT);
        tenantlist.add(instrument);
        var instrument2 = new Instrument();
        instrument2.setActive(true);
        instrument2.setDescription("test2");
        instrument2.setInstrumentType(InstrumentType.TENANT);
        tenantlist.add(instrument2);
        return Mono.just(tenantlist).flatMapMany(Flux::fromIterable);*/
        return instrumentClient.listTenants();
    }


    /** Transactions: **/

    @Override
    public Mono<String> saveTransaction(Transaction transaction) {
        return Mono.fromCallable(() -> {

            sendMessage("validateTransactionRequest-out-0",
                    new Event<>(CREATE, transaction.toString(), transaction));
            return "transaction saved:"+transaction;
        }).subscribeOn(publishEventScheduler);
    }

    @Override
    public Mono<String> delTransaction(String transactionId) {
        return Mono.fromCallable(() -> {

            sendMessage("validateTransactionRequest-out-0",
                    new Event<>(DELETE, transactionId, transactionId));
            return "delete transaction queued:"+transactionId;
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
    public Mono<String> delRecurrentTransfer(String recurrentTransactionId) {
        return Mono.fromCallable(() -> {

            sendMessage("recurrentTransactionaAproved-out-0",
                    new Event<>(DELETE, recurrentTransactionId, recurrentTransactionId));
            return "delete recurrentTransaction queued:"+recurrentTransactionId;
        }).subscribeOn(publishEventScheduler);
    }

    @Override
    public Mono<String> processRecurrentTransaction() {
        return Mono.fromCallable(() -> {

            sendMessage("processRecurrentTransactions-out-0",
                    new Event<>(START, "processRecurrentTransactions", null));
            return "process recurrent Transactions started:";
        }).subscribeOn(publishEventScheduler);
    }

    @Override
    public Flux<Transaction> listTransactions(LocalDate startDate, LocalDate endDate) {
        return transactionClient.listTransactions(startDate, endDate);
    }
    @Override
    public Flux<RecurrentTransaction> listRecurrentTransactions() {
        return transactionClient.listRecurrentTransactions();
    }



    /** MarketData: **/

    @Override
    public Mono<String> loadNewMarketData() {
        return Mono.fromCallable(() -> {

            sendMessage("loadNewMarketDataProcessor-out-0",
                    new Event<>(START, "load", null));
            return "MarketData loading started:";
        }).subscribeOn(publishEventScheduler);
    }

    @Override
    public Mono<EndOfDayPrices> getEndOfDayPrices(String businesskey) {
        return marketdataClient.getEndOfDayPrices(businesskey);
    }


    /** Valuation: **/

    @Override
    public Mono<ValueCurve> getValueCurve(String businesskey, LocalDate startDate, LocalDate endDate) {
        return valuationClient.getValueCurve(businesskey, startDate, endDate);
    }

    @Override
    public Mono<Double> getValue(String businesskey, LocalDate date) {
        return valuationClient.getValue(businesskey, date);
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
