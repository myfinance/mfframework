package de.hf.myfinance.restapi;

import de.hf.myfinance.restmodel.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Tag(name = "CompositeApi", description =
        "${api.common.description}")
public interface CompositeApi {

    @GetMapping("/")
    String index();

    @GetMapping("/helloInstrumentService")
    Instrument helloInstrumentService();

    @PostMapping(
            value    = "/addinstrument",
            consumes = "application/json",
            produces = "application/json")
    Mono<String> saveInstrument(@RequestBody Instrument instrument);

    @PostMapping(
            value    = "/saveTransaction",
            consumes = "application/json",
            produces = "application/json")
    Mono<String>  saveTransaction(@RequestBody Transaction transaction);

    @PostMapping(
            value    = "/saveRecurrentTransaction",
            consumes = "application/json",
            produces = "application/json")
    Mono<String> saveRecurrentTransaction(@RequestBody RecurrentTransaction transaction);

    @PostMapping(
            value    = "/processRecurrentTransaction",
            produces = "application/json")
    Mono<String> processRecurrentTransaction();

    @DeleteMapping(
            value    = "/delTransaction",
            consumes = "application/json",
            produces = "application/json")
    Mono<String> delTransaction(@PathVariable String transactionId);

    @PostMapping(
            value    = "/loadNewMarketData",
            consumes = "application/json",
            produces = "application/json")
    Mono<String> loadNewMarketData();


    @GetMapping(value = "/endOfDayPrices/{businesskey}", produces = "application/json")
    Mono<EndOfDayPrices> getEndOfDayPrices(@PathVariable String businesskey);

    @GetMapping(value = "/getinstrumentvalues/{tenantBusinesskey}", produces = "application/json")
    ValueCurve getInstrumentValues(@PathVariable String tenantBusinesskey, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate);

    @GetMapping(value = "/recurrenttransactions", produces = "application/json")
    Flux<RecurrentTransaction> listRecurrentTransactions();
}
