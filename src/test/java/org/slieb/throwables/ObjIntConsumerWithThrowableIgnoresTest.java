package org.slieb.throwables;
import org.junit.Test;
import static org.slieb.throwables.ObjIntConsumerWithThrowable.castObjIntConsumerWithThrowable;
public class ObjIntConsumerWithThrowableIgnoresTest {
 @Test
 public void testThrowExceptionWithNoIgnores() {
    castObjIntConsumerWithThrowable((v1, v2) -> {
      throw new Exception("expected error");
    }).thatThrowsNothing().accept(null, 0);
 }

}
