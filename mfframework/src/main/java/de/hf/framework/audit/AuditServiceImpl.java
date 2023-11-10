package de.hf.framework.audit;

import de.hf.framework.exceptions.MFException;
import de.hf.framework.exceptions.MsgKey;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AuditServiceImpl implements AuditService {
    private static final Logger LOG = LoggerFactory.getLogger(AuditServiceImpl.class);
    PublishLogEventHandler eventHandler;

    public AuditServiceImpl(PublishLogEventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public void saveMessage(String message, Severity severity, String messagetype) {
        saveMessage(message, severity, messagetype, "NA");
    }

    @Override
    public void saveMessage(String message, Severity severity, String messagetype, String user) {
        var auditType = AuditType.DEBUG;
        switch(severity) {
            case DEBUG ->  auditType = AuditType.DEBUG;
            case INFO ->  auditType = AuditType.USERINFO;
            case WARN ->  auditType = AuditType.ERROR;
            case ERROR -> auditType = AuditType.ERROR;
            case FATAL -> auditType = AuditType.FATAL;
        }

        saveMessage(message, severity, messagetype, user, auditType);
    }

    @Override
    public void saveMessage(String message, Severity severity, String messagetype, String user, AuditType auditType) {
        log4jMsg(message, severity, messagetype, user);
        publishLogToWebSocket(auditType, message, user);
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

    private void log4jMsg(String message, Severity severity, String messagetype, String user) {
        var combineMsg = messagetype + " from user " + user + ": " + message;
        switch(severity) {
            case DEBUG -> LOG.debug(combineMsg);
            case INFO -> LOG.info(combineMsg);
            case WARN -> LOG.warn(combineMsg);
            case ERROR -> LOG.error(combineMsg);
            case FATAL -> LOG.error(combineMsg);
        }
    }

    private void publishLogToWebSocket(AuditType auditType, String message, String user){
        if(!auditType.equals(AuditType.DEBUG)) {
            eventHandler.sendPublishLogEvent(LocalDateTime.now()+" : "+message+" by user " +user, auditType.name() );
        }
    }
}

