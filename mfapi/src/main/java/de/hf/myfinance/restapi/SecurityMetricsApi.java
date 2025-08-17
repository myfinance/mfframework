package de.hf.myfinance.restapi;

import org.springframework.web.bind.annotation.GetMapping;

import de.hf.myfinance.restmodel.SecurityMetrics;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;

@Tag(name = "SecurityMetricsApi", description =
        "${api.common.description}")
public interface SecurityMetricsApi {

	@GetMapping("/")
    String index();

    @GetMapping(value = "/securityMetrics", produces = "application/json")
    Flux<SecurityMetrics> getSecurityMetrics();
} 