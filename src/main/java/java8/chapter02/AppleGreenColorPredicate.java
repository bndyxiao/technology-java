package java8.chapter02;

import java8.Apple;

/**
 * @author: huangzhb
 * @Date: 2019年10月31日 15:43:29
 * @Description:
 */
public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
