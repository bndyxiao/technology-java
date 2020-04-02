import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author: huangzhb
 * @Date: 2019年04月10日 09:22:52
 * @Description:
 */
public interface Formula {

    double calculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }

    static void main(String[] args) {
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };

        formula.calculate(100);

        System.out.println(formula.sqrt(16));

        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        /*Collections.sort(names, new Comparator<String>(){

            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });*/

        System.out.println(names);

        Collections.sort(names, (String a, String b) ->
            b.compareTo(a)
        );
        System.out.println(names);
    }
}
