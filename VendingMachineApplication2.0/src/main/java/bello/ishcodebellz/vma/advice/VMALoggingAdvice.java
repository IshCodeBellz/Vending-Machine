package bello.ishcodebellz.vma.advice;

import bello.ishcodebellz.vma.exceptions.FilePersistenceException;
import org.aspectj.lang.JoinPoint;
import bello.ishcodebellz.vma.dao.VMAAuditDao;

public class VMALoggingAdvice {
    private VMAAuditDao auditDao;

    public VMALoggingAdvice(VMAAuditDao auditDao) {
        this.auditDao = auditDao;
    }

    public void createTransactionAuditEntry(JoinPoint jp, Object returnValue) {
        String auditEntry = jp.getSignature().getName();
        auditEntry += returnValue;
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (FilePersistenceException fpe) {
            System.err.println("ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
// updated audit entry to show whats being purchased, how much is being deposited, actions occuring in the application
 // error occurs then 'ERROR: Could not create audit entry in LoggingAdvice.' will be displayed
    public void createExceptionAuditEntry(JoinPoint jp, Exception e) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName();
        for (Object arg : args) {
            auditEntry += " |Attempted " + arg + " |Exception " + e.getMessage() + " ";
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (FilePersistenceException fpe) {
            System.err.println("ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
}
