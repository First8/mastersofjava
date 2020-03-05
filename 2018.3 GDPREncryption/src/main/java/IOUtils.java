import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

// ripped from Apache Commons IO Utils since we do not support jars (yet)
public class IOUtils {
    
    public static final int EOF = -1;
    
    public static boolean contentEquals(final File file1, final File file2) throws IOException {
        final boolean file1Exists = file1.exists();
        if (file1Exists != file2.exists()) {
            return false;
        }

        if (!file1Exists) {
            // two not existing files are equal
            return true;
        }

        if (file1.isDirectory() || file2.isDirectory()) {
            // don't want to compare directory contents
            throw new IOException("Can't compare directories, only files");
        }

        if (file1.length() != file2.length()) {
            // lengths differ, cannot be equal
            return false;
        }

        if (file1.getCanonicalFile().equals(file2.getCanonicalFile())) {
            // same file
            return true;
        }

        try (InputStream input1 = new FileInputStream(file1);
             InputStream input2 = new FileInputStream(file2)) {
            return contentEquals(input1, input2);
        }
    }

    public static boolean contentEquals(InputStream input1, InputStream input2)
            throws IOException {
        if (input1 == input2) {
            return true;
        }
        if (!(input1 instanceof BufferedInputStream)) {
            input1 = new BufferedInputStream(input1);
        }
        if (!(input2 instanceof BufferedInputStream)) {
            input2 = new BufferedInputStream(input2);
        }

        int ch = input1.read();
        while (EOF != ch) {
            final int ch2 = input2.read();
            if (ch != ch2) {
                return false;
            }
            ch = input1.read();
        }

        final int ch2 = input2.read();
        return ch2 == EOF;
    }

}
