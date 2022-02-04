package de.hf.myfinance.restapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import de.hf.myfinance.restmodel.Instrument;

@Tag(name = "ValuationService", description =
		"REST API for valuation informations of instruments.")
public interface ValuationService {

	@GetMapping("/")
	String index();

	@Operation(
			summary =
					"${api.valuationService.helloInstrument.description}",
			description =
					"${api.valuationService.helloInstrument.notes}")
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
	@GetMapping(value = "/helloInstrument/{instrumentId}", produces = "application/json")
	Instrument helloInstrument(@PathVariable int instrumentId);

	@GetMapping("/helloException")
	Instrument helloException();

	@GetMapping("/helloInstrumentService")
	Instrument helloInstrumentService();
}