package de.hf.myfinance.mfinstrumentclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

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
import de.hf.myfinance.restapi.InstrumentService;
import de.hf.myfinance.restmodel.Instrument;
import de.hf.myfinance.exception.MFMsgKey;


@Component
public class MFInstrumentClient implements InstrumentService {
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
            LOG.debug("Found a string:", aString);
      
            return aString;
      
          } catch (HttpClientErrorException ex) {
      
            switch (ex.getStatusCode()) {
              case NOT_FOUND:
                throw new NotFoundException(getErrorMessage(ex));
              case UNPROCESSABLE_ENTITY:
                throw new InvalidInputException(getErrorMessage(ex));
              case INTERNAL_SERVER_ERROR:
                throw new MFException(MFMsgKey.UNSPECIFIED, getErrorMessage(ex));  
      
              default:
                LOG.warn("Got an unexpected HTTP error: {}, will rethrow it", ex.getStatusCode());
                LOG.warn("Error body: {}", ex.getResponseBodyAsString());
                throw ex;
            }
          }       
    }


    @Override
    public Instrument getInstrument(int productId) {

        try {
          String url = instrumentServiceUrl + "/instrument/" + productId;
          LOG.debug("Will call getProduct API on URL: {}", url);
    
          Instrument instrument = restTemplate.getForObject(url, Instrument.class);
          LOG.debug("Found a product with id: {}", instrument.getInstrumentid());
    
          return instrument;
    
        } catch (HttpClientErrorException ex) {
    
          switch (ex.getStatusCode()) {
            case NOT_FOUND:
              throw new NotFoundException(getErrorMessage(ex));
            case UNPROCESSABLE_ENTITY:
              throw new InvalidInputException(getErrorMessage(ex));
            case INTERNAL_SERVER_ERROR:
              throw new MFException(MFMsgKey.UNSPECIFIED, getErrorMessage(ex));                         
    
            default:
              LOG.warn("Got an unexpected HTTP error: {}, will rethrow it", ex.getStatusCode());
              LOG.warn("Error body: {}", ex.getResponseBodyAsString());
              throw ex;
          }
        }
      }

      private String getErrorMessage(HttpClientErrorException ex) {
        try {
          return mapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
        } catch (IOException ioex) {
          return ex.getMessage();
        }
      }
}