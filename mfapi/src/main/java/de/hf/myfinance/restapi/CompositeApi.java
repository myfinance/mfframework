package de.hf.myfinance.restapi;

import de.hf.myfinance.restmodel.Instrument;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;

@Tag(name = "CompositeApi", description =
        "${api.common.description}")
public interface CompositeApi {

    @GetMapping("/")
    String index();

    @GetMapping("/helloInstrumentService")
    Instrument helloInstrumentService();
}
