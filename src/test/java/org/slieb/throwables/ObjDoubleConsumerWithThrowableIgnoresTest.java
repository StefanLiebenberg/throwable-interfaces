package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.ObjDoubleConsumerWithThrowable.castObjDoubleConsumerWithThrowable;
public class ObjDoubleConsumerWithThrowableIgnoresTest {
 @Test
 public void testThrowExceptionWithNoIgnores() {
    castObjDoubleConsumerWithThrowable((v1, v2) -> {
      throw new Exception("expected error");
    }).thatThrowsNothing().accept(null, 0);
 }

}
