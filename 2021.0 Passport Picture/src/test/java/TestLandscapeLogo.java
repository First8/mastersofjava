import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.junit.Test;

public class TestLandscapeLogo {

	private static final String RED_CROSS_100_50 = "                                                                                                    \n" + 
			"                                                                                                    \n" + 
			"                                                                                                    \n" + 
			"                                                                                                    \n" + 
			"                                                                                                    \n" + 
			"                                                                                                    \n" + 
			"                                                                                                    \n" + 
			"                                                                                                    \n" + 
			"                                                                                                    \n" + 
			"                                                                                                    \n" + 
			"                                                                                                    \n" + 
			"                                              ]  ]                                                  \n" + 
			"                                              BUBBBBBBU                                             \n" + 
			"                                              BBBBBBBBU                                             \n" + 
			"                                              BBBBBBBBU                                             \n" + 
			"                                              BBBBBBBBU                                             \n" + 
			"                                              BBBBBBBBU                                             \n" + 
			"                                              BBBBBBBBU                                             \n" + 
			"                                              BBBBBBBBU                                             \n" + 
			"                                              BBBBBBBBU                                             \n" + 
			"                                     ]U]UU]U[]BBBBBBBBB]U[]U]jU                                     \n" + 
			"                                     UBBBBBBBBBBBBBBBBBBBBBBBBU                                     \n" + 
			"                                     UBBBBBBBBBBBBBBBBBBBBBBBBB                                     \n" + 
			"                                     jBBBBBBBBBBBBBBBBBBBBBBBBB                                     \n" + 
			"                                     jBBBBBBBBBBBBBBBBBBBBBBBBB                                     \n" + 
			"                                     [BBBBUBBBBBBBBBBBBBBBBBBBB]                                    \n" + 
			"                                     [BBBBBBBBBBBBBBBBBBBUBBBBB                                     \n" + 
			"                                     UBBBBBBBBBBBBBBBBBBBBBBBBB                                     \n" + 
			"                                     UBBBBBBBBBBBBBBBBBBBUBBBBB]                                    \n" + 
			"                                         ]    BBBBBBBB]]    ]                                       \n" + 
			"                                              BBBBBBBBU                                             \n" + 
			"                                              BBBBBBBBU                                             \n" + 
			"                                              BBBBBBBB[                                             \n" + 
			"                                              BBBBBBBB[                                             \n" + 
			"                                              BBBBBBBB[                                             \n" + 
			"                                              BBBBBBBB[                                             \n" + 
			"                                              BBBBBBBB[                                             \n" + 
			"                                              jBUB[B[jU                                             \n" + 
			"                                                                                                    \n" + 
			"                                                                                                    \n" + 
			"                                                                                                    \n" + 
			"                                                                                                    \n" + 
			"                                                                                                    \n" + 
			"                                                                                                    \n" + 
			"                                                                                                    \n" + 
			"                                                                                                    \n" + 
			"                                                                                                    \n" + 
			"                                                                                                    \n" + 
			"                                                                                                    \n" + 
			"                                                                                                    \n";

	private static final String NLJUG_40_20 = "                        B[U             \n" + 
			"                        B[U             \n" + 
			"              U   [U]   BUU             \n" + 
			"              BB[UBjBU  B[U             \n" + 
			"              Bjj  UB[  Bjj             \n" + 
			"              [B    UB  jBU             \n" + 
			"              Bj    B[  Bjj             \n" + 
			"              jB    [B  jBU             \n" + 
			"         B[B  jB    UB  jBU  UB         \n" + 
			"         [BU  [B    BB  [B[  BB         \n" + 
			"                   ]                    \n" + 
			"         BjB  jB   ]UB   UUBB[[UB       \n" + 
			"         UBU  BB    BU] [B[] UUBj       \n" + 
			"         BB[  B[    jB  BB]   BjB       \n" + 
			"         jBU  BB    BB  B[]   [UB       \n" + 
			"         jBU  BB    BB  BUU   UBj       \n" + 
			"         BB[  UBj] B[B  UjBB]BBUB       \n" + 
			"         jBU  jBBjBUBB   UB[BUjBU       \n" + 
			"         BB[            U     BjB       \n" + 
			"        [BjU            jBB   B[]       \n";
	
    private static InputStream resource(String name) throws IOException {
    	return Thread.currentThread().getContextClassLoader().getResource(name).openStream();
    }

    @Test
    public void testRedCrossLogo() throws FileNotFoundException, IOException {
        BufferedImage img = ImageIO.read(resource("red-cross-logo.jpg"));
        BufferedImage scaledImg = ImageUtil.scaleImage(100, 50, img);
        String image = ImageUtil.toTextRaster(scaledImg, 100, 50).toString();
        System.out.println("After scaling:");
        System.out.println(image);
        
        assertEquals(RED_CROSS_100_50, image);
    }


    @Test
    public void testNljugLogo() throws FileNotFoundException, IOException {
        BufferedImage img = ImageIO.read(resource("nljug.jpg"));
        BufferedImage scaledImg = ImageUtil.scaleImage(80, 50, img);
        String image = ImageUtil.toTextRaster(scaledImg, 40, 20).toString();
        System.out.println("After scaling:");
        System.out.println(image);
        
        assertEquals(NLJUG_40_20, image);
    }

}
