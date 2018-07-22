import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Grzesiek on 2018-07-22
 */

@AllArgsConstructor
@Getter
@Setter
public class Digit {
    private String name;
    private List<Integer> digit;

    @Override
    public String toString() {
        return "Digit{" +
                "name='" + name + '\'' +
                ", digit=" + digit +
                '}';
    }
}
