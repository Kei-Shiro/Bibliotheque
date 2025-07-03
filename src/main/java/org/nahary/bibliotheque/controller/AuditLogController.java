package org.nahary.bibliotheque.controller;

import org.nahary.bibliotheque.entity.AuditLog;
import org.nahary.bibliotheque.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit-logs")
public class AuditLogController {

    @Autowired
    private AuditLogService auditLogService;

    @GetMapping
    public List<AuditLog> getAllLogs() {
        return auditLogService.getAllLogs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditLog> getLogById(@PathVariable Long id) {
        return auditLogService.getLogById(id);
    }

    @PostMapping
    public AuditLog createLog(@RequestBody AuditLog auditLog) {
        return auditLogService.createLog(auditLog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLog(@PathVariable Long id) {
        return auditLogService.deleteLog(id);
    }
}
