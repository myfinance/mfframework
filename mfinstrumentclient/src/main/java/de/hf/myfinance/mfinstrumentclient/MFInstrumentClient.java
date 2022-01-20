package de.hf.myfinance.mfinstrumentclient;

import static org.springframework.http.HttpMethod.GET;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import de.hf.myfinance.exceptions.InvalidInputException;
import de.hf.myfinance.exceptions.NotFoundException;
import de.hf.myfinance.utils.HttpErrorInfo;
import de.hf.myfinance.restapi.InstrumentService;
import de.hf.myfinance.restmodel.Instrument;


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
      
              default:
                LOG.warn("Got an unexpected HTTP error: {}, will rethrow it", ex.getStatusCode());
                LOG.warn("Error body: {}", ex.getResponseBodyAsString());
                throw ex;
            }
          }       
    }


    public Instrument getProduct(int productId) {

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