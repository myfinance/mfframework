package de.hf.myfinance.restapi;

import de.hf.myfinance.restmodel.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Tag(name = "CompositeApi", description =
        "${api.common.description}")
public interface CompositeApi {

    /** Instruments: **/

    @GetMapping("/")
    String index();

    @GetMapping("/helloInstrumentService")
    Instrument helloInstrumentService();

    @PostMapping(
            value    = "/addinstrument",
            consumes = "application/json",
            produces = "application/json")
    Mono<String> saveInstrument(@RequestBody Instrument instrument);

    @GetMapping(value = "/instruments", produces = "application/json")
    Flux<Instrument> listInstruments();

    @GetMapping(value = "/instrumentsfortenant", produces = "application/json")
    Flux<Instrument> listInstrumentsForTenant(@RequestParam String tenantbusinesskey);

    @GetMapping(value = "/activeinstrumentsfortenant", produces = "application/json")
    Flux<Instrument> listActiveInstrumentsForTenant(@RequestParam String tenantbusinesskey);

    @GetMapping(value = "/instrumentsbytype", produces = "application/json")
    Flux<Instrument> listInstrumentsByType(@RequestParam String tenantbusinesskey, @RequestParam InstrumentType instrumentType);

    @GetMapping(value = "/tenants", produces = "application/json")
    Flux<Instrument> listTenants();




    /** Transactions: **/

    @PostMapping(
            value    = "/saveTransaction",
            consumes = "application/json",
            produces = "application/json")
    Mono<String>  saveTransaction(@RequestBody Transaction transaction);
    @DeleteMapping(
            value    = "/delTransaction",
            consumes = "application/json",
            produces = "application/json")
    Mono<String> delTransaction(@PathVariable String transactionId);

    @PostMapping(
            value    = "/saveRecurrentTransaction",
            consumes = "application/json",
            produces = "application/json")
    Mono<String> saveRecurrentTransaction(@RequestBody RecurrentTransaction transaction);

    @DeleteMapping(
            value    = "/delrecurrenttransfer",
            consumes = "application/json",
            produces = "application/json")
    Mono<String> delRecurrentTransfer(@PathVariable String recurrentTransactionId);

    @PostMapping(
            value    = "/processRecurrentTransaction",
            produces = "application/json")
    Mono<String> processRecurrentTransaction();

    @GetMapping(value = "/transactions", produces = "application/json")
    Flux<Transaction> listTransactions(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate);
    @GetMapping(value = "/recurrenttransactions", produces = "application/json")
    Flux<RecurrentTransaction> listRecurrentTransactions();




    /** MarketData: **/

    @PostMapping(
            value    = "/loadNewMarketData",
            consumes = "application/json",
            produces = "application/json")
    Mono<String> loadNewMarketData();

    @GetMapping(value = "/endOfDayPrices", produces = "application/json")
    Mono<EndOfDayPrices> getEndOfDayPrices(@RequestParam String businesskey);


    /** Valuation: **/

    @GetMapping(value = "/getvaluecurve/{businesskey}", produces = "application/json")
    Mono<ValueCurve> getValueCurve(@PathVariable String businesskey, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate);

    @GetMapping(value = "/getvalue/{businesskey}", produces = "application/json")
    Mono<Double> getValue(@PathVariable String businesskey, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);
}
