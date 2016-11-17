package org.slieb.throwables;

import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class ThrownHandler extends Handler {

    private final AtomicReference<LogRecord> lastRecord;

    public ThrownHandler() {
        lastRecord = new AtomicReference<>();
    }

    public LogRecord getLastRecord() {
        return lastRecord.get();
    }

    @Override
    public void publish(LogRecord record) {
        lastRecord.set(record);
    }

    /**
     * Flush any buffered output.
     */
    @Override
    public void flush() {

    }

    /**
     * Close the <tt>Handler</tt> and free all associated resources.
     * <p>
     * The close method will perform a <tt>flush</tt> and then close the
     * <tt>Handler</tt>.   After close has been called this <tt>Handler</tt>
     * should no longer be used.  Method calls may either be silently
     * ignored or may throw runtime exceptions.
     *
     * @throws SecurityException if a security manager exists and if
     *                           the caller does not have <tt>LoggingPermission("control")</tt>.
     */
    @Override
    public void close() throws SecurityException {

    }
}
