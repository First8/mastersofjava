import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.junit.Test;

public class TestSquareLogo {

	private static final String NLJUG_30_30 = 
			"                    B[B       \n" + 
			"                    UBj       \n" + 
			"                    UBj       \n" + 
			"        ]]] UUj     BB[       \n" + 
			"        [B]jBB[B[   jBB       \n" + 
			"        B[BUBUBjB]  jBB       \n" + 
			"        jBB   [[B[  [BU       \n" + 
			"       ]UBj    BB[  [BU       \n" + 
			"        UBU   ]UBU  B[B       \n" + 
			"        BUB    BUj  BB[       \n" + 
			"       ]UBU    [B[  jBB       \n" + 
			"  [UU   BB[    BjB  [Bj   UUU \n" + 
			"  BjB   [B[    BB[  UBj   BjB \n" + 
			"  B[B   BB[    BjB  [BU   BUB \n" + 
			"  ]UU   ]j]   ]]U]  U]U   U]j \n" + 
			"                              \n" + 
			"  U ]    U     ]   ]   jB[  ]]\n" + 
			"  BjB   BBU    [BU   BBUBBUjBB\n" + 
			"  UBB   B[B    B[B  [B[j  ]BB[\n" + 
			"  BjB   BB[    BB[  BUB    UBB\n" + 
			"  UBj   jBB    jBj  [Bj    [jB\n" + 
			"  BUB   BB[    BBU  BB[    jBj\n" + 
			"  BBU  ][Bj   ]UBj  [Bj    BUB\n" + 
			"  B[B   BjB   ]BUB  UBB    BUB\n" + 
			"  B[B   BUB]  [BjB  ]BBUjjBjBB\n" + 
			"  jBB   jBBUB[BUBj   jBBjBBUBU\n" + 
			"  BBU    BB[Bj BUB    ]jBU ]BB\n" + 
			"  jBB       ]        ]]    B[B\n" + 
			"  BB[          ]    [BU]   B[B\n" + 
			"B[BB[               ]BUBjBjBB]\n";

	private static final String RED_CROSS_50_50 = 
			"                                                  \n" + 
			"                                                  \n" + 
			"                                                  \n" + 
			"                                                  \n" + 
			"                                                  \n" + 
			"                                                  \n" + 
			"                                                  \n" + 
			"                                                  \n" + 
			"                                                  \n" + 
			"                                                  \n" + 
			"                                                  \n" + 
			"                                                  \n" + 
			"                                                  \n" + 
			"                                                  \n" + 
			"                                                  \n" + 
			"                                                  \n" + 
			"                      [UjB[U                      \n" + 
			"                      BBBBB[                      \n" + 
			"                     ]BBBBBj                      \n" + 
			"                      BBBBBB                      \n" + 
			"                      BBUBBB                      \n" + 
			"                     ]BBBBBj                      \n" + 
			"                UUUUUjBBBBBBjjjjjj                \n" + 
			"                BBBBBBBBBBBBBBBBBB                \n" + 
			"                BBBBBBBBBBBBBBBBBB                \n" + 
			"               ]BBBBBBBBBBBBBBBBBU                \n" + 
			"                BBBBBBBBBBBBBBBBBB                \n" + 
			"                BUBBBBBBBBBBBBBBBB                \n" + 
			"                U ]]]UBBBBBjU ]]]                 \n" + 
			"                      BBBBBB                      \n" + 
			"                      BBBBBB                      \n" + 
			"                     ]BBBBBU                      \n" + 
			"                      BBBBBB                      \n" + 
			"                      BBBBBB                      \n" + 
			"                      U]U ]U                      \n" + 
			"                                                  \n" + 
			"                                                  \n" + 
			"                                                  \n" + 
			"                                                  \n" + 
			"                                                  \n" + 
			"                                                  \n" + 
			"                                                  \n" + 
			"                                                  \n" + 
			"                                                  \n" + 
			"                                                  \n" + 
			"                                                  \n" + 
			"                                                  \n" + 
			"                                                  \n" + 
			"                                                  \n" + 
			"                                                  \n";
	
    private static InputStream resource(String name) throws IOException {
    	return Thread.currentThread().getContextClassLoader().getResource(name).openStream();
    }

    @Test
    public void testRedCrossLogo() throws FileNotFoundException, IOException {
        BufferedImage img = ImageIO.read(resource("red-cross-logo.jpg"));
        BufferedImage scaledImg = ImageUtil.scaleImage(50, 50, img);
        String image = ImageUtil.toTextRaster(scaledImg, 50, 50).toString();
        System.out.println("After scaling:");
        System.out.println(image);
        
        assertEquals(RED_CROSS_50_50, image);
    }


    @Test
    public void testNljugLogo() throws FileNotFoundException, IOException {
        BufferedImage img = ImageIO.read(resource("nljug.jpg"));
        BufferedImage scaledImg = ImageUtil.scaleImage(50, 50, img);
        String image = ImageUtil.toTextRaster(scaledImg, 30, 30).toString();
        System.out.println("After scaling:");
        System.out.println(image);
        
        assertEquals(NLJUG_30_30, image);
    }

}
