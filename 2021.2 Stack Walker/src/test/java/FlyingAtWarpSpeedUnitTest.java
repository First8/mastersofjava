import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;

public class FlyingAtWarpSpeedUnitTest {
    private final RisingStackWalker stackWalker = new RisingStackWalker();
    private final Battleship battleship = new Battleship();

    @Test
    public void testStackWalker_MysteryCodes() {
        // lets compute the first 8 secret codes with StackWalker!
        Assert.assertEquals(0, stackWalker.getSecretCodeUsingStreams(0));
        Assert.assertEquals(50, stackWalker.getSecretCodeUsingStreams(1));
        Assert.assertEquals(62, stackWalker.getSecretCodeUsingStreams(2));
        Assert.assertEquals(62, stackWalker.getSecretCodeUsingStreams(3));
        Assert.assertEquals(62, stackWalker.getSecretCodeUsingStreams(4));
        Assert.assertEquals(387, stackWalker.getSecretCodeUsingStreams(5));
        Assert.assertEquals(465, stackWalker.getSecretCodeUsingStreams(6));
        Assert.assertEquals(522, stackWalker.getSecretCodeUsingStreams(7));

        // if the mystery code is implemented correctly for n == 15, then the battleship can start safely.
        Assert.assertThat(stackWalker.getSecretCodeUsingStreams(15), not(equalTo("battleship crash")));
    }


}
