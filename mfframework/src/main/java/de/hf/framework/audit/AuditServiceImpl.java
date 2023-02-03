package de.hf.framework.audit;

import de.hf.framework.exceptions.MFException;
import de.hf.framework.exceptions.MsgKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AuditServiceImpl implements AuditService {
    private static final Logger LOG = LoggerFactory.getLogger(AuditServiceImpl.class);

    @Override
    public void saveMessage(String message, Severity severity, String messagetype, String user) {
        log4jMasg(message, severity, messagetype, user);
    }

    private void log4jMasg(String message, Severity severity, String messagetype, String user) {
        var combineMsg = messagetype + " from user " + user + ": " + message;
        switch(severity) {
            case DEBUG -> LOG.debug(combineMsg);
            case INFO -> LOG.info(combineMsg);
            case WARN -> LOG.warn(combineMsg);
            case ERROR -> LOG.error(combineMsg);
        }
    }

    @Override
    public void saveMessage(String message, Severity severity, String messagetype) {
        saveMessage(message, severity, messagetype, "NA");
    }

    @Override
    public void throwException(String message, String messagetype, MsgKey msgKey) {
        saveMessage(message, Severity.ERROR, messagetype, "NA");
        throw new MFException(msgKey, message);
    }

    @Override
    public Mono<Object> handleMonoError(String message, String messagetype, MsgKey msgKey) {
        saveMessage(message, Severity.ERROR, messagetype, "NA");
        return Mono.error(new MFException(msgKey, message));
    }
}

