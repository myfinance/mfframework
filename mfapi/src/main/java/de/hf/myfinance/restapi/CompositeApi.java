package de.hf.myfinance.restapi;

import de.hf.myfinance.restmodel.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Tag(name = "CompositeApi", description =
        "${api.common.description}")
public interface CompositeApi {

    /** Instruments: **/

    @GetMapping("/mf/index")
    String index();

    @GetMapping("/user")
    @ResponseBody
    public Principal user(Principal user);

    @GetMapping("/mf/helloInstrumentService")
    Instrument helloInstrumentService();

    @PostMapping(
            value    = "/mf/saveinstrument",
            consumes = "application/json",
            produces = "application/json")
    Mono<String> saveInstrument(@RequestBody Instrument instrument);

    @GetMapping(value = "/mf/instruments", produces = "application/json")
    Flux<Instrument> listInstruments();

    @GetMapping(value = "/mf/instrumentsfortenant", produces = "application/json")
    Flux<Instrument> listInstrumentsForTenant(@RequestParam String tenantbusinesskey);

    /* this return all editable instruments for a tenant. That means all instruments of this tenant and all securities which are not connected to a special tenant */
    @GetMapping(value = "/mf/securitiesandinstrumentsfortenant", produces = "application/json")
    Flux<Instrument> listSecuritiesAndInstrumentsForTenant(@RequestParam String tenantbusinesskey);

    @GetMapping(value = "/mf/activeinstrumentsfortenant", produces = "application/json")
    Flux<Instrument> listActiveInstrumentsForTenant(@RequestParam String tenantbusinesskey);

    @GetMapping(value = "/mf/instrumentsbytype", produces = "application/json")
    Flux<Instrument> listInstrumentsByType(@RequestParam String tenantbusinesskey, @RequestParam InstrumentType instrumentType);

    @GetMapping(value = "/mf/tenants", produces = "application/json")
    Flux<Instrument> listTenants();

    @GetMapping(value = "/mf/accounts", produces = "application/json")
    Flux<Instrument> listAccounts(@RequestParam String tenantbusinesskey);

    @GetMapping(value = "/mf/budgets", produces = "application/json")
    Flux<Instrument> listBudgets(@RequestParam String tenantbusinesskey);




    /** Transactions: **/

    @PostMapping(
            value    = "/mf/saveTransaction",
            consumes = "application/json",
            produces = "application/json")
    Mono<String>  saveTransaction(@RequestBody Transaction transaction);
    @PostMapping(
        value    = "/mf/saveTransactions",
        consumes = "application/json",
        produces = "application/json")
    Mono<String>  saveTransactions(@RequestBody List<Transaction> transactions);
    @DeleteMapping(
            value    = "/mf/delTransaction/{transactionId}",
            consumes = "application/json",
            produces = "application/json")
    Mono<String> delTransaction(@PathVariable String transactionId);

    @PostMapping(
            value    = "/mf/saveRecurrentTransaction",
            consumes = "application/json",
            produces = "application/json")
    Mono<String> saveRecurrentTransaction(@RequestBody RecurrentTransaction transaction);

    @DeleteMapping(
            value    = "/mf/delrecurrenttransfer/{recurrentTransactionId}",
            consumes = "application/json",
            produces = "application/json")
    Mono<String> delRecurrentTransfer(@PathVariable String recurrentTransactionId);

    @PostMapping(
            value    = "/mf/processRecurrentTransaction",
            produces = "application/json")
    Mono<String> processRecurrentTransaction();

    @GetMapping(value = "/mf/transactions", produces = "application/json")
    Flux<Transaction> listTransactions(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate);
    @GetMapping(value = "/mf/recurrenttransactions", produces = "application/json")
    Flux<RecurrentTransaction> listRecurrentTransactions();




    /** MarketData: **/

    @PostMapping(
            value    = "/mf/loadNewMarketData",
            consumes = "application/json",
            produces = "application/json")
    Mono<String> loadNewMarketData();

    @GetMapping(value = "/mf/endOfDayPrices", produces = "application/json")
    Mono<EndOfDayPrices> getEndOfDayPrices(@RequestParam String businesskey);

    @PostMapping(
            value    = "/mf/validateSinglePrice",
            consumes = "application/json",
            produces = "application/json")
    Mono<String>  validateSinglePrice(@RequestBody EndOfDayPrice endOfDayPrice);


    /** Valuation: **/

    @GetMapping(value = "/mf/getvaluecurve", produces = "application/json")
    Mono<ValueCurve> getValueCurve(@RequestParam String businesskey, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate);

    @GetMapping(value = "/mf/getvalue", produces = "application/json")
    Mono<Double> getValue(@RequestParam String businesskey, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);

    @GetMapping(value = "/mf/listdetailedaccounts", produces = "application/json")
    Mono<List<InstrumentDetails>> listDetailedAccounts(@RequestParam String tenantbusinesskey, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate duedate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate referencedate);

    @GetMapping(value = "/mf/listdetailedbudgets", produces = "application/json")
    Mono<List<InstrumentDetails>> listDetailedBudets(@RequestParam String tenantbusinesskey, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate duedate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate referencedate);

    @GetMapping(value = "/mf/instrumentdetails", produces = "application/json")
    Mono<InstrumentFullDetails> getInstrumentDetails(@RequestParam String businesskey, 
                                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate duedate, 
                                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate referencedate, 
                                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate starttimeseries, 
                                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endtimeseries,
                                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate firstcashflowdate, 
                                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate lastcashflowdate);

    @GetMapping(value = "/mf/listdetailedsecurities", produces = "application/json")
    Mono<List<SecurityDetails>> listDetailedSecurities(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate duedate, 
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate referencedate);
                                                    
}
