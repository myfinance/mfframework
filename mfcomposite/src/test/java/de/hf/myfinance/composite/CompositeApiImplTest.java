package de.hf.myfinance.composite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import de.hf.myfinance.composite.api.CompositeApiImpl;
import de.hf.myfinance.composite.clients.MFInstrumentClient;
import de.hf.myfinance.composite.clients.MFTransactionClient;
import de.hf.myfinance.composite.clients.MFValuationClient;
import de.hf.myfinance.restmodel.Cashflow;
import de.hf.myfinance.restmodel.Instrument;
import de.hf.myfinance.restmodel.InstrumentDetails;
import de.hf.myfinance.restmodel.InstrumentType;
import de.hf.myfinance.restmodel.ValueCurve;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class CompositeApiImplTest {

    @Mock
    private MFInstrumentClient instrumentClient;

    @Mock
    private MFValuationClient valuationClient;

    @Mock
    private MFTransactionClient transactionClient;

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
        when(valuationClient.getValue("key1", duedate)).thenReturn(Mono.just(100.0));
        when(valuationClient.getValue("key2", duedate)).thenReturn(Mono.just(200.0));

        when(valuationClient.getValue("key1", referencedate)).thenReturn(Mono.just(90.0));
        when(valuationClient.getValue("key2", referencedate)).thenReturn(Mono.just(190.0));

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
        verify(valuationClient).getValue("key1", duedate);
        verify(valuationClient).getValue("key2", duedate);
        verify(valuationClient).getValue("key1", referencedate);
        verify(valuationClient).getValue("key2", referencedate);
    }

    @Test
    void listInstrumentDetailsTest() {
        String businesskey = "key";
        String desc = "bla";
        LocalDate duedate = LocalDate.now();
        LocalDate referencedate = LocalDate.now().minusDays(1);
        double value = 110.0;
        double referencevalue = 100.0;

        var valueCurve = new TreeMap<LocalDate, Double>();
        valueCurve.put(duedate, value);
        valueCurve.put(referencedate, referencevalue);
        var valueCurveObject = new ValueCurve();
        valueCurveObject.setValueCurve(valueCurve);

        // Mocking instrumentClient response
        Instrument instrument = new Instrument(businesskey, desc, InstrumentType.GIRO, true);
        when(instrumentClient.getInstrument(businesskey)).thenReturn(Mono.just(instrument));

        // Mocking valuationClient responses
        when(valuationClient.getValue(businesskey, duedate)).thenReturn(Mono.just(value));
        when(valuationClient.getValue(businesskey, referencedate)).thenReturn(Mono.just(referencevalue));
        when(valuationClient.getValueCurve(businesskey, duedate, referencedate)).thenReturn(Mono.just(valueCurveObject));

        // Mocking transactionClient responses
        when(transactionClient.getAvgExpensesOfLastYear(businesskey)).thenReturn(Mono.just(10.0));
        var cashflow1 = new Cashflow("bla1", duedate, businesskey, 100.0);
        var cashflow2 = new Cashflow("bla2", duedate, businesskey, 200.0);
        var cashflow3 = new Cashflow("bla3", duedate, businesskey, -50.0);
        var cashflow4 = new Cashflow("bla4", duedate, businesskey, -10.0);
        var cashflows= new ArrayList<Cashflow>();
        cashflows.add(cashflow1); 
        cashflows.add(cashflow2); 
        cashflows.add(cashflow3); 
        cashflows.add(cashflow4); 
        when(valuationClient.listCashflows4Instrument(businesskey, duedate, referencedate)).thenReturn(Flux.fromIterable(cashflows));

        // Testing listDetailedAccounts
        var result = compositeApiImpl.getInstrumentDetails(businesskey, duedate, referencedate, duedate, referencedate, duedate, referencedate).block();

        assertEquals(businesskey, result.getBusinesskey());
        assertEquals(desc, result.getDescription());
        assertEquals(InstrumentType.GIRO, result.getInstrumentType());
        assertEquals(value, result.getAdditionalValues().get("valueDuedate"));
        assertEquals(referencevalue, result.getAdditionalValues().get("valueReferencedate"));
        assertEquals(10, result.getAdditionalValues().get("valueChangeAbs"));
        assertEquals(10, Math.round(result.getAdditionalValues().get("valueChangeRel")));
        assertEquals(2, result.getValueCurve().size());
        assertEquals(value, result.getValueCurve().get(duedate));
        assertEquals(referencevalue, result.getValueCurve().get(referencedate));
        assertEquals(10.0, result.getAdditionalValues().get("avgExpensesOfLastYear"));
        assertEquals(300.0, result.getAdditionalValues().get("sumOfIncome"));
        assertEquals(-60.0, result.getAdditionalValues().get("sumOfExpense"));
        assertEquals(2, result.getIncomeInPeriod().size());
        assertEquals(2, result.getExpensesInPeriod().size());
    }
}