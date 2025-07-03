package org.nahary.bibliotheque.service.implementation;

import org.nahary.bibliotheque.entity.AuditLog;
import org.nahary.bibliotheque.repository.AuditLogRepository;
import org.nahary.bibliotheque.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuditLogImplementation implements AuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Override
    public List<AuditLog> getAllLogs() {
        return auditLogRepository.findAll();
    }

    @Override
    public ResponseEntity<AuditLog> getLogById(Long id) {
        Optional<AuditLog> auditLog = auditLogRepository.findById(id);
        return auditLog.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public AuditLog createLog(AuditLog auditLog) {
        return auditLogRepository.save(auditLog);
    }

    @Override
    public ResponseEntity<Void> deleteLog(Long id) {
        if (auditLogRepository.existsById(id)) {
            auditLogRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
