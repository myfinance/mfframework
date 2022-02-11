package de.hf.myfinance.restapi;

import de.hf.myfinance.restmodel.Tenant;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import de.hf.myfinance.restmodel.Instrument;

@Tag(name = "InstrumentApi", description =
		"${api.common.description}")
public interface InstrumentApi {

	@GetMapping("/")
	String index();

	@GetMapping(value = "/instrument/{instrumentId}", produces = "application/json") 
	Instrument getInstrument(@PathVariable int instrumentId);

	@GetMapping(value = "/instrument/{instrumentId}", produces = "application/json")
	void saveTenant(@PathVariable Tenant tenant);
}