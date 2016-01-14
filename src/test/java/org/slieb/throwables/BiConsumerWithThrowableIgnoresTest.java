package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.BiConsumerWithThrowable.castBiConsumerWithThrowable;
public class BiConsumerWithThrowableIgnoresTest {
 @Test
 public void testThrowExceptionWithNoIgnores() {
    castBiConsumerWithThrowable((v1, v2) -> {
      throw new Exception("expected error");
    }).thatThrowsNothing().accept(null, null);
 }

}
