import java.io.IOException;
import java.nio.file.DirectoryStream;
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
        List<Digit> columns = list.subList(1, list.size())
                .stream()
                .map(x -> x.split(","))
                .map(z -> {
                    return Arrays.stream(z)
                            .map(y -> Integer.parseInt(y))
                            .collect(Collectors.toList());
                })
                .map(x -> {
                    Digit digit = new Digit(String.valueOf(x.get(0)), x.subList(1, x.size()));
                    return digit;
                })
                .collect(Collectors.toList());


        System.out.println(columns.get(0));
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

    public static double distance(Digit a, Digit b) {
        double sum = 0;
        for (int i = 0; i < a.getDigit().size(); i++) {
            sum += (b.getDigit().get(i) - a.getDigit().get(i)) * (b.getDigit().get(i) - a.getDigit().get(i));
        }
        double result = Math.sqrt(sum);
        System.out.println("result: " + result);
        return result;
    }
}