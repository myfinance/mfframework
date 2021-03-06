package de.hf.myfinance.mfinstrumentclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;

import de.hf.myfinance.restmodel.InstrumentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import de.hf.framework.exceptions.InvalidInputException;
import de.hf.framework.exceptions.MFException;
import de.hf.framework.exceptions.NotFoundException;
import de.hf.framework.utils.HttpErrorInfo;
import de.hf.myfinance.restapi.InstrumentApi;
import de.hf.myfinance.restmodel.Instrument;
import de.hf.myfinance.exception.MFMsgKey;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class MFInstrumentClient implements InstrumentApi {
    private static final Logger LOG = LoggerFactory.getLogger(MFInstrumentClient.class);
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;
    private final String instrumentServiceUrl;

    @Autowired
    public MFInstrumentClient(
      RestTemplate restTemplate,
      ObjectMapper mapper,
      @Value("${app.mfinstruments.host}") String instrumentServiceHost,
      @Value("${app.mfinstruments.port}") int instrumentServicePort) {
  
      this.restTemplate = restTemplate;
      this.mapper = mapper;
  
      instrumentServiceUrl = "http://" + instrumentServiceHost + ":" + instrumentServicePort;
    }

    public String index(){
        try {
            LOG.debug("Will call getProduct API on URL: {}", instrumentServiceUrl);
      
            String aString = restTemplate.getForObject(instrumentServiceUrl, String.class);
            LOG.debug("Found a string:{}", aString);
      
            return aString;
      
          } catch (HttpClientErrorException ex) {

            switch (ex.getStatusCode()) {
                case NOT_FOUND -> throw new NotFoundException(getErrorMessage(ex));
                case UNPROCESSABLE_ENTITY -> throw new InvalidInputException(getErrorMessage(ex));
                case INTERNAL_SERVER_ERROR -> throw new MFException(MFMsgKey.UNSPECIFIED, getErrorMessage(ex));
                default -> {
                    LOG.warn("Got an unexpected HTTP error: {}, will rethrow it", ex.getStatusCode());
                    LOG.warn("Error body: {}", ex.getResponseBodyAsString());
                    throw ex;
                }
            }
          }       
    }

    @Override
    public Mono<Instrument> getInstrument(String businesskey) {
        try {
            String url = instrumentServiceUrl + "/instrumentblocking/" + businesskey;
            LOG.debug("Will call getProduct API on URL: {}", url);

            Mono<Instrument> instrument = restTemplate.getForObject(url, Mono.class);

            return instrument;

        } catch (HttpClientErrorException ex) {

            switch (ex.getStatusCode()) {
                case NOT_FOUND -> throw new NotFoundException(getErrorMessage(ex));
                case UNPROCESSABLE_ENTITY -> throw new InvalidInputException(getErrorMessage(ex));
                case INTERNAL_SERVER_ERROR -> throw new MFException(MFMsgKey.UNSPECIFIED, getErrorMessage(ex));
                default -> {
                    LOG.warn("Got an unexpected HTTP error: {}, will rethrow it", ex.getStatusCode());
                    LOG.warn("Error body: {}", ex.getResponseBodyAsString());
                    throw ex;
                }
            }
        }
    }

    @Override
    public Flux<Instrument> listInstruments() {
        return null;
    }

    @Override
    public Flux<Instrument> listInstrumentsForTenant(String businesskey) {
        return null;
    }

    @Override
    public Flux<Instrument> listActiveInstrumentsForTenant(String tenantbusinesskey) {
        return null;
    }

    @Override
    public Flux<Instrument> listInstrumentsByType(String tenantbusinesskey, InstrumentType instrumentType) {
        return null;
    }

    @Override
    public Flux<Instrument> listTenants() {
        return null;
    }

    @Override
    public Mono<Instrument> saveInstrument(Instrument instrument) {
        return null;
    }

    private String getErrorMessage(HttpClientErrorException ex) {
        try {
          return mapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
        } catch (IOException ioex) {
          return ex.getMessage();
        }
      }
}