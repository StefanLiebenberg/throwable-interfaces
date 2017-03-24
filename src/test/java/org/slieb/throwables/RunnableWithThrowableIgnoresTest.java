package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.RunnableWithThrowable.castRunnableWithThrowable;
public class RunnableWithThrowableIgnoresTest {
 @Test
 public void testThrowExceptionWithNoIgnores() {
    castRunnableWithThrowable(() -> {
      throw new Exception("expected error");
    }).thatThrowsNothing().run();
 }

}
