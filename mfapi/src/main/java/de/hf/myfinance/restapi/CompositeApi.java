package de.hf.myfinance.restapi;

import de.hf.myfinance.restmodel.Instrument;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    Mono<Instrument> saveInstrument(@RequestBody Instrument instrument);
}
