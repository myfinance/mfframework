package de.hf.myfinance.restapi;

import de.hf.myfinance.restmodel.Instrument;
import de.hf.myfinance.restmodel.Transaction;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

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

    @DeleteMapping(
            value    = "/delTransaction",
            consumes = "application/json",
            produces = "application/json")
    Mono<String> delTransaction(@PathVariable String transactionId);
}
