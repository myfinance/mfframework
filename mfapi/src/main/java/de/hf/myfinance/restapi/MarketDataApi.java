package de.hf.myfinance.restapi;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;

@Tag(name = "MarketDataApi", description =
        "${api.common.description}")
public interface MarketDataApi {

    @GetMapping("/")
    String index();
}

