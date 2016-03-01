package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.ClosureWithThrowable.castClosureWithThrowable;
public class ClosureWithThrowableIgnoresTest {
 @Test
 public void testThrowExceptionWithNoIgnores() {
    castClosureWithThrowable(() -> {
      throw new Exception("expected error");
    }).thatThrowsNothing().call();
 }

}
