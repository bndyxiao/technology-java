package java8.chapter02;

import java8.Apple;

/**
 * @author: huangzhb
 * @Date: 2019年10月31日 15:42:37
 * @Description:
 */
public class AppleHeavyWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
