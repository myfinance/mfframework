package de.hf.myfinance.restapi;

import de.hf.myfinance.restmodel.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.time.LocalDate;

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

    @GetMapping(value = "/mf/activeinstrumentsfortenant", produces = "application/json")
    Flux<Instrument> listActiveInstrumentsForTenant(@RequestParam String tenantbusinesskey);

    @GetMapping(value = "/mf/instrumentsbytype", produces = "application/json")
    Flux<Instrument> listInstrumentsByType(@RequestParam String tenantbusinesskey, @RequestParam InstrumentType instrumentType);

    @GetMapping(value = "/mf/tenants", produces = "application/json")
    Flux<Instrument> listTenants();




    /** Transactions: **/

    @PostMapping(
            value    = "/mf/saveTransaction",
            consumes = "application/json",
            produces = "application/json")
    Mono<String>  saveTransaction(@RequestBody Transaction transaction);
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


    /** Valuation: **/

    @GetMapping(value = "/mf/getvaluecurve", produces = "application/json")
    Mono<ValueCurve> getValueCurve(@RequestParam String businesskey, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate);

    @GetMapping(value = "/mf/getvalue", produces = "application/json")
    Mono<Double> getValue(@RequestParam String businesskey, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);
}
