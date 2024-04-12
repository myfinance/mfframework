package de.hf.myfinance.composite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import de.hf.myfinance.composite.api.CompositeApiImpl;
import de.hf.myfinance.composite.clients.MFInstrumentClient;
import de.hf.myfinance.composite.clients.MFValuationClient;
import de.hf.myfinance.restmodel.Instrument;
import de.hf.myfinance.restmodel.InstrumentDetails;
import de.hf.myfinance.restmodel.InstrumentType;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class CompositeApiImplTest {

    @Mock
    private MFInstrumentClient instrumentClient;

    @Mock
    private MFValuationClient valuationClient;

    @InjectMocks
    private CompositeApiImpl compositeApiImpl;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void listDetailedAccountsTest() {
        String tenantbusinesskey = "tenant1";
        LocalDate duedate = LocalDate.now();
        LocalDate referencedate = LocalDate.now().minusDays(1);


        // Mocking instrumentClient response
        Instrument instrument1 = new Instrument("key1", "Instrument 1", InstrumentType.GIRO, true);
        Instrument instrument2 = new Instrument("key2", "Instrument 2", InstrumentType.GIRO, true);
        when(instrumentClient.listAccounts(tenantbusinesskey)).thenReturn(Flux.just(instrument1, instrument2));

        // Mocking valuationClient responses
        HashMap<String, Double> valuesForDueday = new HashMap<>();
        valuesForDueday.put("key1", 100.0);
        valuesForDueday.put("key2", 200.0);
        when(valuationClient.getValues(Arrays.asList("key1", "key2"), duedate)).thenReturn(Flux.just(valuesForDueday));

        HashMap<String, Double> valuesForReferencedate = new HashMap<>();
        valuesForReferencedate.put("key1", 90.0);
        valuesForReferencedate.put("key2", 190.0);
        when(valuationClient.getValues(Arrays.asList("key1", "key2"), referencedate)).thenReturn(Flux.just(valuesForReferencedate));

        // Testing listDetailedAccounts
        Mono<List<InstrumentDetails>> result = compositeApiImpl.listDetailedAccounts(tenantbusinesskey, duedate, referencedate);

        StepVerifier.create(result)
                .expectNextMatches(list -> list.size() == 2 &&
                        list.get(0).getValue() == 100.0 &&
                        list.get(0).getReferenceValue() == 90.0 &&
                        list.get(1).getValue() == 200.0 &&
                        list.get(1).getReferenceValue() == 190.0)
                .verifyComplete();

        // Verify interactions
        verify(instrumentClient).listAccounts(tenantbusinesskey);
        verify(valuationClient).getValues(Arrays.asList("key1", "key2"), duedate);
        verify(valuationClient).getValues(Arrays.asList("key1", "key2"), referencedate);
    }
}