package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.IntConsumerWithThrowable.castIntConsumerWithThrowable;
public class IntConsumerWithThrowableIgnoresTest {
 @Test
 public void testThrowExceptionWithNoIgnores() {
    castIntConsumerWithThrowable((v1) -> {
      throw new Exception("expected error");
    }).thatThrowsNothing().accept(0);
 }

}
