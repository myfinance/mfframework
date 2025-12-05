package de.hf.myfinance.restapi;

import de.hf.myfinance.restmodel.Cashflow;
import de.hf.myfinance.restmodel.PortfolioMetrics;
import de.hf.myfinance.restmodel.Position;
import de.hf.myfinance.restmodel.ValuationType;
import de.hf.myfinance.restmodel.ValueCurve;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Tag(name = "ValuationApi", description =
		"${api.common.description}")
public interface ValuationApi {

	@GetMapping("/")
	String index();

	@Operation(
			summary =
					"${api.ValuationApi.getValueCurve.description}",
			description =
					"${api.ValuationApi.getValueCurve.notes}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description =
					"${api.responseCodes.ok.description}"),
			@ApiResponse(responseCode = "400", description =
					"${api.responseCodes.badRequest.description}"),
			@ApiResponse(responseCode = "404", description =
					"${api.responseCodes.notFound.description}"),
			@ApiResponse(responseCode = "422", description =
					"${api.responseCodes.unprocessableEntity.description}")
	})
	@GetMapping(value = "/getvaluecurve", produces = "application/json")
	Mono<ValueCurve> getValueCurve(@RequestParam String businesskey, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate, @RequestParam ValuationType valuationType);

	@GetMapping(value = "/getvalue", produces = "application/json")
	Mono<Double> getValue(@RequestParam String businesskey, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @RequestParam ValuationType valuationType);

	// returns the timestamp of the last update of the value curve for the given businesskey 
	@GetMapping(value = "/getvaluets", produces = "application/json")
	Mono<LocalDateTime> getValueTs(@RequestParam String businesskey);

	@GetMapping(value = "/cashflows4instrument", produces = "application/json")
    Flux<Cashflow> listCashflows4Instrument(@RequestParam String businesskey, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate);

	@GetMapping(value = "/avgexpensesoflastyear", produces = "application/json")
    Mono<Double> getAvgExpensesOfLastYear(@RequestParam String businesskey);

	@GetMapping(value = "/positions", produces = "application/json")
    Flux<Position> getPositions(@RequestParam List<String> depotKeys);

	@PostMapping(value = "/recalcandgetvaluecurve", produces = "application/json")
	Mono<ValueCurve> recalcAndGetValueCurve(@RequestParam String businesskey);

	@GetMapping(value = "/recalcAllCurves", produces = "application/json")
	Mono<String> recalcAllCurves();

	@GetMapping(value = "/linkedvalues", produces = "application/json")
	Mono<Map<String,Double>> getLinkedValues(@RequestParam String businesskey, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate valueDate, @RequestParam ValuationType valuationType);

	@GetMapping(value = "/portfoliometrics", produces = "application/json")
    Flux<PortfolioMetrics> getPortfolioMetrics();
}
