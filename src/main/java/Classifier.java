import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by szypows_local on 22.07.2018.
 */
public class Classifier {
    public static void main(String[] args) {

        System.out.println(fileRead("trainingsample.csv"));

    }


    public static List<String> fileRead(String pathIn) {

        List<String> list = new ArrayList<>();

        try {
            list = Files.readAllLines(Paths.get(pathIn));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}