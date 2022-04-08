package de.hf.framework.audit;

import org.springframework.stereotype.Component;

@Component
public class AuditServiceImpl implements AuditService {


    @Override
    public void saveMessage(String message, Severity severity, String messagetype, String user) {

    }

    @Override
    public void saveMessage(String message, Severity severity, String messagetype) {
        saveMessage(message, severity, messagetype, "NA");
    }

}

