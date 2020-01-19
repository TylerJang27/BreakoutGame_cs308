package breakout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.StringBuilder;

/**
 * A class to read in text from a file and return it as a String
 *  Implementation based on tutorial from https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
 */
public class TextReader {

    /**
     * Reads a file and returns its contents
     * @param filePath a String of the file name
     * @return a String with linebreaks of the file's contents
     * @throws FileNotFoundException if file name invalid
     */
    public static String readFile(String filePath) throws FileNotFoundException {
        StringBuilder contents = new StringBuilder();
        File file = new File(filePath);
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            contents.append(sc.nextLine() + "\n");
        }
        return contents.toString();
    }
}
