package de.hf.myfinance.restapi;

import de.hf.myfinance.restmodel.Trade;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(name = "TransactionApi", description =
        "${api.common.description}")
public interface TransactionApi {

    @GetMapping("/")
    String index();

    /**
     *
     * @param trade RequestBody is necessery to force deserialisation of complex objects
     */
    @PostMapping(
            value    = "/addtrade",
            consumes = "application/json",
            produces = "application/json")
    void addTrade(@RequestBody Trade trade);
}
