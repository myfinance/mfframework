package de.hf.myfinance.restapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import de.hf.myfinance.restmodel.Instrument;


public interface ValuationService {

	@GetMapping("/")
	String index();

	@GetMapping(value = "/helloInstrument/{instrumentId}", produces = "application/json")
	Instrument helloInstrument(@PathVariable int instrumentId);

	@GetMapping("/helloException")
	Instrument helloException();

	@GetMapping("/helloInstrumentService")
	Instrument helloInstrumentService();
}