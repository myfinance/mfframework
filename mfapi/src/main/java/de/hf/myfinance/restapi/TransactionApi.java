package de.hf.myfinance.restapi;

import de.hf.myfinance.restmodel.RecurrentTransaction;
import de.hf.myfinance.restmodel.Trade;
import de.hf.myfinance.restmodel.Transaction;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@Tag(name = "TransactionApi", description =
        "${api.common.description}")
public interface TransactionApi {

    @GetMapping("/")
    String index();

    @PostMapping(
            value    = "/addtrade",
            consumes = "application/json",
            produces = "application/json")
    Mono<String> addTrade(@RequestBody Trade trade);

    @PostMapping(
            value    = "/updatetrade",
            consumes = "application/json",
            produces = "application/json")
    Mono<String> updateTrade(@RequestBody Trade trade);

    @DeleteMapping(
            value    = "/delrecurrenttransfer",
            consumes = "application/json",
            produces = "application/json")
    Mono<String> delRecurrentTransfer(@PathVariable String recurrentTransactionId);

    @DeleteMapping(
            value    = "/delTransfer",
            consumes = "application/json",
            produces = "application/json")
    Mono<String> delTransfer(@PathVariable String transactionId);

    @PostMapping(
            value    = "/updateRecurrentTransaction",
            consumes = "application/json",
            produces = "application/json")
    Mono<String> updateRecurrentTransaction(@RequestBody RecurrentTransaction recurrentTransaction);

    @PostMapping(
            value    = "/addRecurrentTransaction",
            consumes = "application/json",
            produces = "application/json")
    Mono<String> addRecurrentTransaction(@RequestBody RecurrentTransaction recurrentTransaction);

    @PostMapping(
            value    = "/addTransaction",
            consumes = "application/json",
            produces = "application/json")
    Mono<String> addTransaction(@RequestBody Transaction transaction);

    @PostMapping(
            value    = "/updateTransaction",
            consumes = "application/json",
            produces = "application/json")
    Mono<String>  updateTransaction(@RequestBody Transaction transaction);
}
