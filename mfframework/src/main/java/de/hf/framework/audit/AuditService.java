package de.hf.framework.audit;

import de.hf.framework.exceptions.MFException;
import de.hf.framework.exceptions.MsgKey;
import reactor.core.publisher.Mono;


public interface AuditService {
    void saveMessage(String message, Severity severity, String messagetype, String user, AuditType auditType);
    void saveMessage(String message, Severity severity, String messagetype, String user);
    void saveMessage(String message, Severity severity, String messagetype);
    void throwException(String message, String messagetype, MsgKey msgKey)  throws MFException;
    Mono<Object> handleMonoError(String message, String messagetype, MsgKey msgKey);
}
