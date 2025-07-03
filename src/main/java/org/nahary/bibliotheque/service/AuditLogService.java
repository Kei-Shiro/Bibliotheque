package org.nahary.bibliotheque.service;

import org.nahary.bibliotheque.entity.AuditLog;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuditLogService {

    List<AuditLog> getAllLogs();

    ResponseEntity<AuditLog> getLogById(Long id);

    AuditLog createLog(AuditLog auditLog);

    ResponseEntity<Void> deleteLog(Long id);
}
