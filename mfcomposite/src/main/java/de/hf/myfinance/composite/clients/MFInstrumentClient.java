package de.hf.myfinance.composite.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.hf.framework.audit.AuditService;
import de.hf.framework.exceptions.MFException;
import de.hf.framework.utils.HttpErrorInfo;
import de.hf.myfinance.exception.MFMsgKey;
import de.hf.myfinance.restapi.InstrumentApi;
import de.hf.myfinance.restmodel.Instrument;
import de.hf.myfinance.restmodel.InstrumentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;


@Component
public class MFInstrumentClient implements InstrumentApi {
    private final AuditService auditService;
    private final WebClient webClient;
    protected static final String AUDIT_MSG_TYPE="MFInstrumentClient_User_Event";
    private final ObjectMapper mapper;
    private final String instrumentServiceUrl;

    @Autowired
    public MFInstrumentClient(AuditService auditService,
                              WebClient.Builder webClient,
                              ObjectMapper mapper,
                              @Value("${app.mfinstruments.host}") String instrumentServiceHost,
                              @Value("${app.mfinstruments.port}") int instrumentServicePort) {
        this.webClient = webClient.build();
        this.mapper = mapper;
        this.auditService = auditService;
        instrumentServiceUrl = "http://" + instrumentServiceHost + ":" + instrumentServicePort;
    }

    public String index(){
        throw new MFException(MFMsgKey.UNSPECIFIED, "not implemented yet");
    }

    @Override
    public Mono<Instrument> getInstrument(String businesskey) {
        throw new MFException(MFMsgKey.UNSPECIFIED, "not implemented yet");
    }

    @Override
    public Flux<Instrument> listInstruments() {
        return webClient.get().uri(instrumentServiceUrl + "/instruments").retrieve().bodyToFlux(Instrument.class);
    }

    @Override
    public Flux<Instrument> listInstrumentsForTenant(String businesskey) {
        return webClient.get().uri(instrumentServiceUrl + "/instrumentsfortenant?businesskey=" + businesskey)
                .retrieve().bodyToFlux(Instrument.class);
    }

    @Override
    public Flux<Instrument> listActiveInstrumentsForTenant(String tenantbusinesskey) {
        return webClient.get().uri(instrumentServiceUrl + "/activeinstrumentsfortenant?tenantbusinesskey=" + tenantbusinesskey)
                .retrieve().bodyToFlux(Instrument.class);
    }

    @Override
    public Flux<Instrument> listInstrumentsByType(String tenantbusinesskey, InstrumentType instrumentType) {
        return webClient.get().uri(instrumentServiceUrl + "/instrumentsbytype?tenantbusinesskey="
                + tenantbusinesskey + "&instrumentType="
                + instrumentType)
                .retrieve().bodyToFlux(Instrument.class);
    }

    @Override
    public Flux<Instrument> listTenants() {
        return webClient.get().uri(instrumentServiceUrl + "/tenants").retrieve().bodyToFlux(Instrument.class);
    }

    @Override
    public Mono<String> saveInstrument(Instrument instrument) {
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