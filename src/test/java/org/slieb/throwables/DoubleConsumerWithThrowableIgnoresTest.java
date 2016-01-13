package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.DoubleConsumerWithThrowable.castDoubleConsumerWithThrowable;
public class DoubleConsumerWithThrowableIgnoresTest {
 @Test
 public void testThrowExceptionWithNoIgnores() {
    castDoubleConsumerWithThrowable((v1) -> {
      throw new Exception("expected error");
    }).thatIgnoresExceptions().accept(0);
 }

}
