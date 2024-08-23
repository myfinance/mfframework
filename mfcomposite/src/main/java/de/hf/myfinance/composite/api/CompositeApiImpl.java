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
import java.util.HashMap;
import java.util.List;

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
    public Flux<Instrument> listSecuritiesAndInstrumentsForTenant(String tenantbusinesskey) {
        var securities = instrumentClient.listSecurities();
        var instrumentsForTenant = instrumentClient.listInstrumentsForTenant(tenantbusinesskey);
        return Flux.merge(securities, instrumentsForTenant);
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
        return instrumentClient.listTenants();
    }

    @Override
    public Flux<Instrument> listAccounts(String tenantbusinesskey) {
        return instrumentClient.listAccounts(tenantbusinesskey);
    }
    @Override
    public Flux<Instrument> listBudgets(String tenantbusinesskey) {
        return instrumentClient.listBudgets(tenantbusinesskey);
    }


    /** Transactions: **/

    @Override
    public Mono<String> saveTransaction(Transaction transaction) {
        return Mono.fromCallable(() -> {

            sendMessage("validateTransactionRequest-out-0",
                    new Event<>(CREATE, transaction.toString(), transaction));
            return "{\"success\": \"transaction"+transaction.getDescription() +" saved\"}";
        }).subscribeOn(publishEventScheduler);
    }

    @Override
    public Mono<String> saveTransactions(List<Transaction> transactions) {

        return Mono.fromCallable(() -> {
            transactions.forEach(t->{
                sendMessage("validateTransactionRequest-out-0",
                new Event<>(CREATE, t.toString(), t));
            });

            return "{\"success\": \"transactions saved\"}";
        }).subscribeOn(publishEventScheduler);
    }

    @Override
    public Mono<String> delTransaction(String transactionId) {
        return Mono.fromCallable(() -> {

            sendMessage("validateTransactionRequest-out-0",
                    new Event<>(DELETE, transactionId, transactionId));
            return "{\"success\": \"delete transaction queued:"+transactionId +" \"}";
        }).subscribeOn(publishEventScheduler);
    }

    @Override
    public Mono<String> saveRecurrentTransaction(RecurrentTransaction transaction) {
        return Mono.fromCallable(() -> {

            sendMessage("validateRecurrentTransactionRequest-out-0",
                    new Event<>(CREATE, transaction.toString(), transaction));
            return "{\"success\": \"recurrentTransaction"+transaction.getDescription() +" saved\"}";
        }).subscribeOn(publishEventScheduler);
    }

    @Override
    public Mono<String> delRecurrentTransfer(String recurrentTransactionId) {
        return Mono.fromCallable(() -> {

            sendMessage("recurrentTransactionaAproved-out-0",
                    new Event<>(DELETE, recurrentTransactionId, recurrentTransactionId));
            return "{\"success\": \"delete recurrentTransaction queued:"+recurrentTransactionId +" \"}";
        }).subscribeOn(publishEventScheduler);
    }

    @Override
    public Mono<String> processRecurrentTransaction() {
        return Mono.fromCallable(() -> {

            sendMessage("processRecurrentTransactions-out-0",
                    new Event<>(START, "processRecurrentTransactions", null));
            return "{\"success\": \"process recurrent Transactions started \"}";
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
            return "{\"success\": \"MarketData loading started \"}";
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

    @Override
    public Mono<List<InstrumentDetails>> listDetailedAccounts(String tenantbusinesskey, LocalDate duedate,
            LocalDate referencedate) {
        return listAccounts(tenantbusinesskey).collectList().flatMap(a->collectDetails(a,duedate,referencedate));
    }
    @Override
    public Mono<List<InstrumentDetails>> listDetailedBudets(String tenantbusinesskey, LocalDate duedate,
            LocalDate referencedate) {

        return listBudgets(tenantbusinesskey).collectList().flatMap(a->collectDetails(a,duedate,referencedate));
    }

    private Mono<List<InstrumentDetails>> collectDetails(List<Instrument> instruments, LocalDate duedate,
    LocalDate referencedate) {
        var businesskeys = new ArrayList<String>();
        var instrumentDetailMap = new HashMap<String, InstrumentDetails>();
        instruments.forEach(i->{
            var instrumentDetails = new InstrumentDetails();
            instrumentDetails.setBusinesskey(i.getBusinesskey());
            instrumentDetails.setDescription(i.getDescription());
            instrumentDetails.setLiquiditytype(i.getLiquidityType());
            businesskeys.add(i.getBusinesskey());
            instrumentDetailMap.put(i.getBusinesskey(), instrumentDetails);
        });

        //request the values for alle instruments,collect the flux and reduce it to one mono
        Mono<HashMap<String, Double>> valuesForDueday = valuationClient.getValues(businesskeys, duedate)
        .reduce(new HashMap<String, Double>(), (accumulator, next) -> {
            accumulator.putAll(next);
            return accumulator; // Return the modified accumulator
        });
        Mono<HashMap<String, Double>> valuesForReferencedate = valuationClient.getValues(businesskeys, referencedate)
        .reduce(new HashMap<String, Double>(), (accumulator, next) -> {
            accumulator.putAll(next);
            return accumulator; // Return the modified accumulator
        });


        return Mono.zip(valuesForDueday, valuesForReferencedate).map(tuple ->{
            tuple.getT1().keySet().forEach(x->{
                var details = instrumentDetailMap.get(x);
                details.setValue(tuple.getT1().get(x));
                details.setReferenceValue(tuple.getT2().get(x));
                instrumentDetailMap.put(x, details);
            });
            return new ArrayList<>(instrumentDetailMap.values()); 
        });
    }

    @Override
    public Mono<InstrumentFullDetails> getInstrumentDetails(String businesskey, LocalDate duedate, LocalDate referencedate, LocalDate starttimeseries, LocalDate endtimeseries, LocalDate firstcashflowdate, LocalDate lastcashflowdate) {
        var instrumentFullDetails = instrumentClient.getInstrument(businesskey).flatMap(i-> collectInstrumentFullDetails(i, duedate, referencedate));
        var valueCurve = valuationClient.getValueCurve(businesskey, starttimeseries, endtimeseries);
        var avgExpensesOfLastYear = transactionClient.getAvgExpensesOfLastYear(businesskey);
        var cashflows = transactionClient.listCashflows4Instrument(businesskey, firstcashflowdate, lastcashflowdate);

        var result = Mono.zip(instrumentFullDetails, valueCurve).map(tuple ->{
            var  details  = tuple.getT1();
            details.setValueCurve(tuple.getT2().getValueCurve());
            return details; 
        }).zipWith(avgExpensesOfLastYear).map(tuple -> {
            var  details  = tuple.getT1();
            details.addAdditionalValue("avgExpensesOfLastYear", tuple.getT2());
            return details; 
        }).zipWith(cashflows.collectList()).map(tuple -> {
            var  details  = tuple.getT1();
            var expenses = tuple.getT2().stream().filter(c->c.getValue()<0).toList();
            var incomes = tuple.getT2().stream().filter(c->c.getValue()>0).toList();
            details.setExpensesInPeriod(expenses);
            details.setIncomeInPeriod(incomes);
            details.addAdditionalValue("sumOfIncome", incomes.stream().map(c->c.getValue()).reduce(0.0, Double::sum));
            details.addAdditionalValue("sumOfExpense", expenses.stream().map(c->c.getValue()).reduce(0.0, Double::sum));
            return details; 
        });


        return result;

    }

    private Mono<InstrumentFullDetails> collectInstrumentFullDetails(Instrument instrument, LocalDate duedate, LocalDate referencedate){
        var valueDuedate = valuationClient.getValue(instrument.getBusinesskey(), duedate);
        var valueReferencedate = valuationClient.getValue(instrument.getBusinesskey(), referencedate);
        //transactionClient.listTransactions()

        return Mono.zip(valueDuedate, valueReferencedate).map(tuple ->{
            var fullDetails = new InstrumentFullDetails();
            fullDetails.setBusinesskey(instrument.getBusinesskey());
            fullDetails.setDescription(instrument.getDescription());
            fullDetails.setInstrumentType(instrument.getInstrumentType());
            fullDetails.addAdditionalValue("valueDuedate", tuple.getT1());
            fullDetails.addAdditionalValue("valueReferencedate", tuple.getT2());
            fullDetails.addAdditionalValue("valueChangeAbs", tuple.getT1()-tuple.getT2());
            fullDetails.addAdditionalValue("valueChangeRel", ((tuple.getT1()/tuple.getT2())-1)*100);
            return fullDetails; 
        });
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
