package java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: huangzhb
 * @Date: 2019年11月05日 20:26:50
 * @Description:
 */
public class StreamDemo {

    public static void main(String[] args) {

        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", false, 530, Dish.Type.OTHER)
        );
        /*
        //List<String> collect = menu.stream().filter(d -> d.getCalories() > 300).map(Dish::getName).limit(3).collect(Collectors.toList());


        //System.out.println(collect);
        Stream<Dish> stream = menu.stream();
        stream.forEach(System.out::println);
        stream.forEach(System.out::println);*/

//        List<String> list = Arrays.asList("0001_to_0001","0001_to_0002","0001_to_0003","0001_to_0004","0001_to_0001");
//        list.stream().distinct().forEach(System.out::println);
        //List<String> list = Arrays.asList("Hello", "World");
        /*List<String[]> collect = list.stream().map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList());*/
        //List<String> collect = list.stream().map(s -> s.split("")).flatMap(Arrays::stream).collect(Collectors.toList());


        //System.out.println(collect);

        //System.out.println("Hello".split(""));
        // Arrays.stream("Hello".split("")).forEach(System.out::println);


        /*List<Integer> list1 = Arrays.asList(1,2,3);
        List<Integer> list2 = Arrays.asList(3,4);
        list1.stream().flatMap(n -> list2.stream().map(x -> new Integer[]{n, x}))
                .collect(Collectors.toList()).forEach(x -> {
            System.out.println("("+x[0] + "," + x[1] + ")");
        });*/

        //menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(d -> System.out.println(d.getName()));

        List<Integer> someNumbers = Arrays.asList(1,2,3,4,5);
        Optional<Integer> firstSquareDivisibleByThree =
                someNumbers.stream()
                           .map(x -> x * x)
                           .filter(x -> x % 3 == 0)
                           .findFirst();


    }
}
