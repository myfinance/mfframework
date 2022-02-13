package de.hf.myfinance.restapi;

import de.hf.myfinance.restmodel.Tenant;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import de.hf.myfinance.restmodel.Instrument;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "InstrumentApi", description =
		"${api.common.description}")
public interface InstrumentApi {

	@GetMapping("/")
	String index();

	@GetMapping(value = "/instrument/{instrumentId}", produces = "application/json") 
	Instrument getInstrument(@PathVariable int instrumentId);

	/**
	 * Sample usage, see below.
	 *
	 * curl -X POST $HOST:$PORT/instrument \
	 *   -H "Content-Type: application/json" --data \
	 *   '{"businesskey":"testkey","description":"desc","isactive":true}'
	 *
	 * @param tenant A JSON representation of the new review
	 */
	@PostMapping(
			value    = "/instrument",
			consumes = "application/json",
			produces = "application/json")
	void saveTenant(@RequestBody Tenant tenant);
}