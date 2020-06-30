package bello.ishcodebellz.vma.dao;

import bello.ishcodebellz.vma.exceptions.FilePersistenceException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class VMAAuditDaoFileImpl implements VMAAuditDao {
    private static final String AUDIT_FILE = "VMAauditlog.txt";

    @Override
    public void writeAuditEntry(String entry) throws FilePersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new FilePersistenceException("Could not persist audit information.", e);
        }

        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }
}
