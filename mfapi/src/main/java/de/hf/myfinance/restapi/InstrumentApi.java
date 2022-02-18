package de.hf.myfinance.restapi;

import de.hf.myfinance.restmodel.Tenant;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import de.hf.myfinance.restmodel.Instrument;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "InstrumentApi", description =
		"${api.common.description}")
public interface InstrumentApi {

	@GetMapping("/")
	String index();

	@GetMapping(value = "/instrument/{instrumentId}", produces = "application/json") 
	Instrument getInstrument(@PathVariable int instrumentId);

	@GetMapping(value = "/instruments", produces = "application/json")
	List<Instrument> listInstruments();

	@GetMapping(value = "/tenants", produces = "application/json")
	List<Tenant> listTenants();

	@PostMapping(
			value    = "/addtenant",
			consumes = "application/json",
			produces = "application/json")
	void addTenant(@RequestBody String description);
}