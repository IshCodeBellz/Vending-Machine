package bello.ishcodebellz.vma.dao;

import bello.ishcodebellz.vma.exceptions.FilePersistenceException;

public interface VMAAuditDao {
    void writeAuditEntry(String entry) throws FilePersistenceException;
}
