package de.hf.myfinance.composite.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import de.hf.framework.exceptions.MFException;
import de.hf.myfinance.exception.MFMsgKey;
import de.hf.myfinance.restapi.SecurityMetricsApi;
import de.hf.myfinance.restmodel.SecurityMetrics;
import reactor.core.publisher.Flux;

@Component
public class MFSecurityMetricsClient implements SecurityMetricsApi {

    private final WebClient webClient;
    protected static final String AUDIT_MSG_TYPE="MFSecurityMetricsClient_User_Event";
    private final String serviceUrl;

    public MFSecurityMetricsClient(WebClient.Builder webClient,
                              @Value("${app.mfsecuritymetrics.host}") String serviceHost,
                              @Value("${app.mfsecuritymetrics.port}") int servicePort) {
        this.webClient = webClient.build();
        serviceUrl = "http://" + serviceHost + ":" + servicePort;
    }

    public String index(){
        throw new MFException(MFMsgKey.UNSPECIFIED, "not implemented yet");
    }

    @Override
    public Flux<SecurityMetrics> getSecurityMetrics() {
        return webClient.get().uri(serviceUrl + "/securityMetrics").retrieve().bodyToFlux(SecurityMetrics.class);
    }

}
