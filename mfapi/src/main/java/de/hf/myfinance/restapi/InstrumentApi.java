package de.hf.myfinance.restapi;

import de.hf.myfinance.restmodel.InstrumentType;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import de.hf.myfinance.restmodel.Instrument;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Tag(name = "InstrumentApi", description =
		"${api.common.description}")
public interface InstrumentApi {

	@GetMapping("/")
	String index();

	@GetMapping(value = "/instrument", produces = "application/json")
	Mono<Instrument> getInstrument(@RequestParam String businesskey);

	@GetMapping(value = "/instruments", produces = "application/json")
	Flux<Instrument> listInstruments();

	@GetMapping(value = "/instrumentsfortenant", produces = "application/json")
	Flux<Instrument> listInstrumentsForTenant(@RequestParam String tenantbusinesskey);

	@GetMapping(value = "/activeinstrumentsfortenant", produces = "application/json")
	Flux<Instrument> listActiveInstrumentsForTenant(@RequestParam String tenantbusinesskey);

	@GetMapping(value = "/instrumentsbytype", produces = "application/json")
	Flux<Instrument> listInstrumentsByType(@RequestParam String tenantbusinesskey, @RequestParam InstrumentType instrumentType);

	@GetMapping(value = "/tenants", produces = "application/json")
	Flux<Instrument> listTenants();

	@GetMapping(value = "/accounts", produces = "application/json")
	Flux<Instrument> listAccounts(@RequestParam String tenantbusinesskey);

	@GetMapping(value = "/budgets", produces = "application/json")
	Flux<Instrument> listBudgets(@RequestParam String tenantbusinesskey);

	@PostMapping(
			value    = "/addinstrument",
			consumes = "application/json",
			produces = "application/json")
	Mono<String> saveInstrument(@RequestBody Instrument instrument);
}