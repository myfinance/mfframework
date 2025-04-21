package de.hf.myfinance.restapi;

import de.hf.myfinance.restmodel.EndOfDayPrices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Tag(name = "MarketDataApi", description =
        "${api.common.description}")
public interface MarketDataApi {

    @GetMapping("/")
    String index();

    @PostMapping(
            value    = "/loadNewMarketData",
            produces = "application/json")
    Mono<String> loadNewMarketData();

    @GetMapping(value = "/endOfDayPrices", produces = "application/json")
    Mono<EndOfDayPrices> getEndOfDayPrices(@RequestParam String businesskey);

    @PostMapping(
            value    = "/mf/validatePrices",
            consumes = "application/json",
            produces = "application/json")
    Mono<String>  validatePrices(@RequestBody EndOfDayPrices endOfDayPrices);
}

