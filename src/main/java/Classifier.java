import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by szypows_local on 22.07.2018.
 */
public class Classifier {
    public static void main(String[] args) {
        List<String> list = fileRead("trainingsample.csv");
        List<Digit> columns = getDigits(list);

        List<String> toVaild = fileRead("validationsample.csv");
        List<Digit> toValidDigits = getDigits(list);

        System.out.println(predict(toValidDigits, columns));
        System.out.println("\nEND\n");
    }

    private static List<Digit> getDigits(List<String> list) {
        return list.subList(1, list.size())
                .stream()
                .map(x -> x.split(","))
                .map(z -> {
                    return Arrays.stream(z)
                            .map(y -> Integer.parseInt(y))
                            .collect(Collectors.toList());
                })
                .map(x -> {
                    Digit digit = new Digit(x.get(0), x.subList(1, x.size()));
                    return digit;
                })
                .collect(Collectors.toList());
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
        return Math.sqrt(sum);

    }

    public static int predict(List<Digit> validationsample, List<Digit> trainingsample) { //returns accuracy of number recognition
        int count = 0;
        for (Digit input : validationsample) {
            List<Record> records = trainingsample.stream()
                    .map(x -> {
                        Record record = new Record(x, distance(input, x));
                        return record;
                    })
                    .sorted(Comparator.comparingDouble(x -> x.getDistnce()))
                    .collect(Collectors.toList());
            System.out.print(".");
            if (records.get(0).getDigit().getLabel() == input.getLabel())
                count++;
        }
        return count / validationsample.size();
    }
}