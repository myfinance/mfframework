package de.hf.myfinance.restapi;

import de.hf.myfinance.restmodel.ValueCurve;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import de.hf.myfinance.restmodel.Instrument;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

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
	@GetMapping(value = "/getvaluecurve/{businesskey}", produces = "application/json")
	ValueCurve getValueCurve(@PathVariable String businesskey, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate);

	@GetMapping(value = "/getvalue/{businesskey}", produces = "application/json")
	ValueCurve getValue(@PathVariable String businesskey, @RequestParam LocalDate date);
}