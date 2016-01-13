package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.LongConsumerWithThrowable.castLongConsumerWithThrowable;
public class LongConsumerWithThrowableIgnoresTest {
 @Test
 public void testThrowExceptionWithNoIgnores() {
    castLongConsumerWithThrowable((v1) -> {
      throw new Exception("expected error");
    }).thatIgnoresExceptions().accept(0);
 }

}
