

import org.junit.Assert;
import org.junit.Test;

public class HiddenTest {
    private RisingStackWalker reflection = new RisingStackWalker();

    @Test
    public void testStackWalker_Index_vs_Code_including_secret_code() {
        Assert.assertEquals(reflection.getSecretCodeUsingStreams(0), 0);
        Assert.assertEquals(reflection.getSecretCodeUsingStreams(1), 50);
        Assert.assertEquals(reflection.getSecretCodeUsingStreams(2), 62);
        Assert.assertEquals(reflection.getSecretCodeUsingStreams(3), 62);
        Assert.assertEquals(reflection.getSecretCodeUsingStreams(4), 62);
        Assert.assertEquals(reflection.getSecretCodeUsingStreams(5), 387);
        Assert.assertEquals(reflection.getSecretCodeUsingStreams(6), 465);
        Assert.assertEquals(reflection.getSecretCodeUsingStreams(7), 522);
        Assert.assertEquals(reflection.getSecretCodeUsingStreams(8), 812);
        Assert.assertEquals(reflection.getSecretCodeUsingStreams(9), 812);
        Assert.assertEquals(reflection.getSecretCodeUsingStreams(10), 1100);
        Assert.assertEquals(reflection.getSecretCodeUsingStreams(11), 1100);
        Assert.assertEquals(reflection.getSecretCodeUsingStreams(12), 1100);
        Assert.assertEquals(reflection.getSecretCodeUsingStreams(13), 1463);
        Assert.assertEquals(reflection.getSecretCodeUsingStreams(14), 1591);
        Assert.assertEquals(reflection.getSecretCodeUsingStreams(15), 1618);
    }
}
