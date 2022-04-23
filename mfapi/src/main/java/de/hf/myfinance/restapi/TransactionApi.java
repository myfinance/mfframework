package de.hf.myfinance.restapi;

import de.hf.myfinance.restmodel.RecurrentTransaction;
import de.hf.myfinance.restmodel.Trade;
import de.hf.myfinance.restmodel.Transaction;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;


@Tag(name = "TransactionApi", description =
        "${api.common.description}")
public interface TransactionApi {

    @GetMapping("/")
    String index();

    @PostMapping(
            value    = "/addtrade",
            consumes = "application/json",
            produces = "application/json")
    void addTrade(@RequestBody Trade trade);

    @PostMapping(
            value    = "/updatetrade",
            consumes = "application/json",
            produces = "application/json")
    void updateTrade(@RequestBody Trade trade);

    @DeleteMapping(
            value    = "/delrecurrenttransfer",
            consumes = "application/json",
            produces = "application/json")
    void delRecurrentTransfer(@PathVariable String recurrentTransactionId);

    @DeleteMapping(
            value    = "/delTransfer",
            consumes = "application/json",
            produces = "application/json")
    void delTransfer(@PathVariable String transactionId);

    @PostMapping(
            value    = "/updateRecurrentTransaction",
            consumes = "application/json",
            produces = "application/json")
    void updateRecurrentTransaction(@RequestBody RecurrentTransaction recurrentTransaction);

    @PostMapping(
            value    = "/addRecurrentTransaction",
            consumes = "application/json",
            produces = "application/json")
    void addRecurrentTransaction(@RequestBody RecurrentTransaction recurrentTransaction);

    @PostMapping(
            value    = "/addTransaction",
            consumes = "application/json",
            produces = "application/json")
    void addTransaction(@RequestBody Transaction transaction);

    @PostMapping(
            value    = "/updateTransaction",
            consumes = "application/json",
            produces = "application/json")
    void updateTransaction(@RequestBody Transaction transaction);
}
