package hust.soict.dsai.garbage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GarbageCreator {
    public static void main(String[] args) {
        String filename = "test.txt"; 
        byte[] inputBytes = { 0 };
        long startTime, endTime;

        try {
            inputBytes = Files.readAllBytes(Paths.get(filename));
            startTime = System.currentTimeMillis();
            String outputString = "";
            for (byte b : inputBytes) {
                outputString += (char)b;
            }
            endTime = System.currentTimeMillis();
            System.out.println("Processing time (String +): " + (endTime - startTime));
            System.out.println("Output length: " + outputString.length());
        } catch (IOException e) {
            System.out.println("Error: Could not read file " + filename + ". Please create it first.");
        }
    }
}
