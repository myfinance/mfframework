package de.hf.myfinance.composite.api;

import de.hf.framework.exceptions.MFException;
import de.hf.framework.utils.ServiceUtil;
import de.hf.myfinance.exception.MFMsgKey;
import de.hf.myfinance.mfinstrumentclient.MFInstrumentClient;
import de.hf.myfinance.restapi.CompositeApi;
import de.hf.myfinance.restmodel.Instrument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompositeApiImpl  implements CompositeApi {
    ServiceUtil serviceUtil;
    MFInstrumentClient instrumentClient;

    @Autowired
    public CompositeApiImpl(ServiceUtil serviceUtil, MFInstrumentClient instrumentClient) {
        this.serviceUtil = serviceUtil;
        this.instrumentClient = instrumentClient;
    }
    @Override
    public String index() {
        return "Hello compositeservice";
    }

    @Override
    public Instrument helloInstrumentService() {
        try{
            return instrumentClient.getInstrument("1").block();
        } catch(MFException e) {
            throw e;
        }
        catch(Exception e) {
            throw new MFException(MFMsgKey.UNSPECIFIED, e.getMessage());
        }

    }
}
