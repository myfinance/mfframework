package de.hf.myfinance.restapi;

import de.hf.myfinance.restmodel.InstrumentType;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import de.hf.myfinance.restmodel.Instrument;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Tag(name = "InstrumentApi", description =
		"${api.common.description}")
public interface InstrumentApi {

	@GetMapping("/")
	String index();

	@GetMapping(value = "/instrument/{businesskey}", produces = "application/json")
	Mono<Instrument> getInstrument(@PathVariable String businesskey);

	@GetMapping(value = "/instruments", produces = "application/json")
	Flux<Instrument> listInstruments();

	@GetMapping(value = "/instrumentsfortenant", produces = "application/json")
	Flux<Instrument> listInstrumentsForTenant(@PathVariable String tenantbusinesskey);

	@GetMapping(value = "/activeinstrumentsfortenant", produces = "application/json")
	Flux<Instrument> listActiveInstrumentsForTenant(@PathVariable String tenantbusinesskey);

	@GetMapping(value = "/instrumentsbytype", produces = "application/json")
	Flux<Instrument> listInstrumentsByType(@PathVariable String tenantbusinesskey, @RequestParam InstrumentType instrumentType);

	@GetMapping(value = "/tenants", produces = "application/json")
	Flux<Instrument> listTenants();

	@PostMapping(
			value    = "/addinstrument",
			consumes = "application/json",
			produces = "application/json")
	Mono<String> saveInstrument(@RequestBody Instrument instrument);
}