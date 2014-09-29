package scalikejdbc4j;

import org.junit.Test;
import scala.Option;
import scala.collection.Seq;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class ConvertersTest {

    @Test
    public void toScalaOption() throws Exception {
        Option<Long> opt = ScalaConverters.toScalaOption(Optional.of(1L));
        assertTrue(opt.isDefined());
        assertThat(opt.get(), is(1L));
    }

    @Test
    public void toScalaSeq() throws Exception {
        Seq<String> names = ScalaConverters.toScalaSeq(Arrays.asList("Alice", "Bob"));
        assertThat(names.length(), is(2));
    }

}
