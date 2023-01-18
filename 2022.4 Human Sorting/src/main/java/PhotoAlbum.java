import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PhotoAlbum {
    private List<String> images = new ArrayList<>();
    private Comparator<String> intelligentComparator;

    public PhotoAlbum() {

    }
    public PhotoAlbum(Comparator<String> intelligentComparator) {
        this.intelligentComparator = intelligentComparator;
    }
    public void insertDirectory(File directory) {
        if (directory.isDirectory()) {
            insertImages(Arrays.asList(directory.list()));
        }
    }
    public void insertImages(List<String> moreImages) {
        System.out.println("IMAGES.INPUT: " + moreImages);
        images.addAll( moreImages );
        if (intelligentComparator!=null) {
            Collections.sort(images, intelligentComparator);
            System.out.println("IMAGES.SORTED: " + images);
        }
    }
    public List<String> getImages() {
        return images;
    }
}
