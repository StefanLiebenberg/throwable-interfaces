package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.ConsumerWithThrowable.castConsumerWithThrowable;
public class ConsumerWithThrowableIgnoresTest {
 @Test
 public void testThrowExceptionWithNoIgnores() {
    castConsumerWithThrowable((v1) -> {
      throw new Exception("expected error");
    }).thatThrowsNothing().accept(null);
 }

}
