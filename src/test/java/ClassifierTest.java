import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by szypows_local on 23.07.2018.
 */
public class ClassifierTest {
    ArrayList<Integer> aPoint = new ArrayList<>();
    Digit a = new Digit(1, aPoint);
    ArrayList<Integer> bPoint = new ArrayList<>();
    Digit b = new Digit(2, bPoint);

    @Before
    public void setup() {
        aPoint.add(1);
        aPoint.add(1);
        aPoint.add(1);
        bPoint.add(2);
        bPoint.add(2);
        bPoint.add(2);
        System.out.println(aPoint);
        System.out.println(bPoint);
    }

    @Test
    public void distance_should_return_sqrt3() {
        double result = Classifier.distance(a, b);
        assertEquals(Math.sqrt(3), result,0.1);
    }
}