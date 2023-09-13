import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.APPEND;

public class FileHelper {

    private final static String RES_PATH = "/Users/aidanthomas/command_line_coding/website";
    
    public static String readFile(String path) {

        StringBuilder s = new StringBuilder();
        Path file = Paths.get(RES_PATH + path);

        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.US_ASCII)) {
            String line;
            while ((line = reader.readLine()) != null) {
                s.append("\n").append(line);
            }

        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

        return s.toString();
    }

    public static void writeFile(String payload) {
        Path path = Paths.get(RES_PATH + "/data.txt");
        byte[] data = payload.getBytes();

        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(path, CREATE, APPEND))) {
            out.write(data, 0, data.length);
        } catch (IOException x) {
            System.err.println(x);
        }
    }



}
