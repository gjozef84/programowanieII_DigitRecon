import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by szypows_local on 22.07.2018.
 */
public class Classifier {
    public static void main(String[] args) {
        List<String> list = fileRead("trainingsample.csv");
        List<List<Integer>> columns = list.subList(1,list.size())
                .stream()
                .map(x->x.split(","))
                .map(z->{
                      return Arrays.stream(z)
                              .map(y->Integer.parseInt(y))
                              .collect(Collectors.toList());
                })
                .collect(Collectors.toList());
        System.out.println(columns);
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