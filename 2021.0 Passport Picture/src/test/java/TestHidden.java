import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.junit.Test;

public class TestHidden {

	private static final String FIRST_40_60 = "                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                     ]] \n" + 
			" [jBB  U]  B[BB]   B[BB  BUBUB]  ]      \n" + 
			"[U     UU  B   B] B[       B ]     ]    \n" + 
			"B]     jU ]B   ]B jU       B     ] ]]   \n" + 
			"BjUU]  B] ]B   [U ]BjU     B]   ]     ] \n" + 
			"B[ ]   B]  BBjBU    U[B    B        ]   \n" + 
			"B]     jU ]B  B]      jU   B]   ]    ]  \n" + 
			"B      [U  j  UB      B]   B]   ]   ]   \n" + 
			"B      [] ]B   [[ [UBB[    j    ]]      \n" + 
			"                                ]      ]\n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n";

	private static final String FIRST8_40_40 = "                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                     ]] \n" + 
			" [jBB  U]  B[BB]   B[BB  BUBUB]  ]      \n" + 
			"[U     UU  B   B] B[       B ]     ]    \n" + 
			"B]     jU ]B   ]B jU       B     ] ]]   \n" + 
			"BjUU]  B] ]B   [U ]BjU     B]   ]     ] \n" + 
			"B[ ]   B]  BBjBU    U[B    B        ]   \n" + 
			"B]     jU ]B  B]      jU   B]   ]    ]  \n" + 
			"B      [U  j  UB      B]   B]   ]   ]   \n" + 
			"B      [] ]B   [[ [UBB[    j    ]]      \n" + 
			"                                ]      ]\n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n" + 
			"                                        \n"; 
	
    private static InputStream resource(String name) throws IOException {
    	return Thread.currentThread().getContextClassLoader().getResource(name).openStream();
    }

    @Test
    public void testLandscapeFirst8Logo() throws FileNotFoundException, IOException {
        BufferedImage img = ImageIO.read(resource("first8-logo.png"));
        BufferedImage scaledImg = ImageUtil.scaleImage(40, 60, img);
        String image = ImageUtil.toTextRaster(scaledImg, 40, 60).toString();
        System.out.println("After scaling:");
        System.out.println(image);
        
        assertEquals(FIRST_40_60, image);
    }


    @Test
    public void testSquareFirst8Logo() throws FileNotFoundException, IOException {
        BufferedImage img = ImageIO.read(resource("first8-logo.png"));
        BufferedImage scaledImg = ImageUtil.scaleImage(40, 40, img);
        String image = ImageUtil.toTextRaster(scaledImg, 40, 40).toString();
        System.out.println("After scaling:");
        System.out.println(image);
        
        assertEquals(FIRST8_40_40, image);
    }

}
