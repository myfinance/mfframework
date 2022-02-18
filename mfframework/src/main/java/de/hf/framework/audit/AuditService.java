package de.hf.framework.audit;

import java.time.LocalDateTime;

public interface AuditService {
    void saveMessage(String message, Severity severity, String messagetype, String user);
    void saveMessage(String message, Severity severity, String messagetype);
}
