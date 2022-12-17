package de.hf.myfinance.restapi;

import de.hf.myfinance.restmodel.EndOfDayPrices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping(
            value    = "/savePrices",
            consumes = "application/json",
            produces = "application/json")
    Mono<Void> savePrices(@RequestBody EndOfDayPrices endOfDayPrices);

    @GetMapping(value = "/endOfDayPrices/{businesskey}", produces = "application/json")
    Mono<EndOfDayPrices> getEndOfDayPrices(@PathVariable String businesskey);
}

