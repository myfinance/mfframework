package de.hf.myfinance.restapi;

import org.springframework.web.bind.annotation.GetMapping;

import de.hf.myfinance.restmodel.Instrument;


public interface ValuationService {

	@GetMapping("/")
	String index();

	@GetMapping("/helloInstrument")
	Instrument helloInstrument();
}