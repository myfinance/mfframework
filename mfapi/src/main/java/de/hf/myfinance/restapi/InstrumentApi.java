package de.hf.myfinance.restapi;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import de.hf.myfinance.restmodel.Instrument;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@GetMapping(value = "/instrumentblocking/{businesskey}", produces = "application/json")
	Instrument getInstrumentBlocking(@PathVariable String businesskey);

	@GetMapping(value = "/instruments", produces = "application/json")
	Flux<Instrument> listInstruments();

	@GetMapping(value = "/instrumentsfortenant", produces = "application/json")
	Flux<Instrument> listInstrumentsForTenant(@PathVariable String businesskey);

	@GetMapping(value = "/tenants", produces = "application/json")
	Flux<Instrument> listTenants();

	@PostMapping(
			value    = "/addinstrument",
			consumes = "application/json",
			produces = "application/json")
	Mono<Instrument> saveInstrument(@RequestBody Instrument instrument);
}