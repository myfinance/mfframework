package de.hf.myfinance.restapi;

import de.hf.myfinance.restmodel.RecurrentTransaction;
import de.hf.myfinance.restmodel.Transaction;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;


@Tag(name = "TransactionApi", description =
        "${api.common.description}")
public interface TransactionApi {

    @GetMapping("/")
    String index();

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


    @DeleteMapping(
            value    = "/delrecurrenttransfer",
            consumes = "application/json",
            produces = "application/json")
    Mono<String> delRecurrentTransfer(@PathVariable String recurrentTransactionId);

    @PostMapping(
            value    = "/addRecurrentTransaction",
            consumes = "application/json",
            produces = "application/json")
    Mono<String> saveRecurrentTransaction(@RequestBody RecurrentTransaction recurrentTransaction);

    @PostMapping(
            value    = "/processRecurrentTransaction",
            produces = "application/json")
    Mono<String> processRecurrentTransaction();


    @GetMapping(value = "/transactions", produces = "application/json")
    Flux<Transaction> listTransactions(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate);

    @GetMapping(value = "/recurrenttransactions", produces = "application/json")
    Flux<RecurrentTransaction> listRecurrentTransactions();
}
