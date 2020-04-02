package java8.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: huangzhb
 * @Date: 2019年11月07日 10:46:22
 * @Description:
 */
public class TestTrader {

    public static void main(String[] args) {
        /*Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactionList = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // 找出2011年发生的所有交易,并按交易额排序(从低到高)
        transactionList.stream().filter(x -> x.getYear() == 2011)
                                .sorted(Comparator.comparing(Transaction::getValue))
                                .collect(Collectors.toList())
                                .forEach(x -> System.out.println(x));

        System.out.println("====================================");

        // 交易员都在哪些不同的城市工作过
        transactionList.stream().map(x -> x.getTrader().getCity()).distinct().collect(Collectors.toList()).forEach(System.out::println);

        // 查找所有来自于剑桥的交易员,并按姓名排序
        System.out.println("====================================");

        transactionList.stream().filter(x -> x.getTrader().getCity().equals("Cambridge")).map(x -> x.getTrader()).distinct().sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList()).forEach(System.out::println);
*/

        Stream<String> stream = Stream.of("Java 8", "Lambdas", "In ", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        Stream<String> emptyStream = Stream.empty();

        int[] numbers = {2,3,5,7,11,13};
        int sum = Arrays.stream(numbers).sum();
    }
}
