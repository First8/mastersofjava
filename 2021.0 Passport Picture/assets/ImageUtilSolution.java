
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;

import org.alcibiade.asciiart.coord.TextBoxSize;
import org.alcibiade.asciiart.image.rasterize.ShapeRasterizer;
import org.alcibiade.asciiart.raster.ExtensibleCharacterRaster;
import org.alcibiade.asciiart.raster.Raster;
import org.alcibiade.asciiart.raster.RasterContext;
import org.alcibiade.asciiart.widget.PictureWidget;
import org.alcibiade.asciiart.widget.TextWidget;

public class ImageUtil {

    public static final Dimension MAX_IMG_DIMENSION = new Dimension(2560, 1440);
    
    /**
     * TODO Implement this.
     * Get a scaled instance of the Image and redraw it on a new Graphics2d. 
     * @param source the original image 
     * @param width the width that it should be scaled to
     * @param height the height that it should be scaled to
     * @return the scaled image
     */
    private static BufferedImage performScale(BufferedImage source, int width, int height) {
        Image scaledImage = source.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = (Graphics2D) result.getGraphics();
        graphics.setComposite(AlphaComposite.Src);
        graphics.drawImage(scaledImage, 0, 0, width, height, Color.WHITE, null);
        graphics.dispose();
        return result;
    }

    /**
     * Scales an image to the requested maximum dimensions honoring aspect
     * ratio.
     * <p>
     * When filling to requested dimensions, the resulting image will always be
     * of the requested dimension. The scaled part will be positioned centered
     * in the resulting image. The background of the resulting image is
     * transparant.
     * </p>
     *
     * @param sourceImage
     *            the image to scale.
     * @param maxWidth
     *            the maximum allowable width.
     * @param maxHeight
     *            the maximum allowable height.
     * @return the resulting image.
     */
    private static BufferedImage scaleAndFillImage(final BufferedImage sourceImage, final int maxWidth, final int maxHeight) {
        int calculatedWidth = sourceImage.getWidth();
        int calculatedHeight = sourceImage.getHeight();

        float sourceRatio = (float) calculatedWidth / (float) calculatedHeight;
        float targetRatio = (float) maxWidth / (float) maxHeight;

        if (sourceRatio >= targetRatio) {
            calculatedWidth = maxWidth;
            calculatedHeight = Math.round(maxWidth / sourceRatio);
        } else {
            calculatedHeight = maxHeight;
            calculatedWidth = Math.round(maxHeight * sourceRatio);
        }

        BufferedImage scaledImage = performScale(sourceImage, calculatedWidth, calculatedHeight);
        if (maxWidth > calculatedWidth || maxHeight > calculatedHeight) {
            return fillImageToDimension(scaledImage, maxWidth, maxHeight);
        }
        return scaledImage;
    }


    // paint image in the center of the requested dimensions using a white
    // background.
    // we do not upscale the image.
    private static BufferedImage fillImageToDimension(BufferedImage source, int width, int height) {
        int xoff = Math.round((width - source.getWidth(null)) / 2.0f);
        int yoff = Math.round((height - source.getHeight(null)) / 2.0f);

        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D) result.getGraphics();
        graphics.setBackground(Color.WHITE);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
        graphics.setComposite(AlphaComposite.Src);
        graphics.drawImage(source, xoff, yoff, source.getWidth(null), source.getHeight(null), new Color(255, 255, 255, 255), null);
        return result;
    }

    public static BufferedImage scaleImage(final int width, final int height, BufferedImage img) throws IOException, MalformedURLException {
        if (isValidImageRequest(width, height)) {
            BufferedImage scaledImage = scaleAndFillImage(img, width, height);
            return scaledImage;
        } else {
            throw new IllegalArgumentException("Image size out of bounds");
        }
    }

    private static boolean isValidImageRequest(final int width, final int height) {
        return width > 0 && height > 0 && width <= MAX_IMG_DIMENSION.width && height <= MAX_IMG_DIMENSION.height;
    }

    public static void debugImage(BufferedImage img, final int width, final int height) {
        System.out.println(toTextRaster(img, width, height));
    }

    public static Raster toTextRaster(BufferedImage img, final int width, final int height) {
        TextWidget widget = new PictureWidget(new TextBoxSize(width, height), img, new ShapeRasterizer());
        Raster raster = new ExtensibleCharacterRaster();
        widget.render(new RasterContext(raster));
        return raster;
    }

}
