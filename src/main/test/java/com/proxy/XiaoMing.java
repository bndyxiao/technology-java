package com.proxy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author: huangzhb
 * @Date: 2019年01月17日 11:31:35
 * @Description:
 */
public class XiaoMing implements Hose {

    @Override
    public void mai() {
        System.out.println("我是小明，我要买房");
    }
}

class Proxy implements Hose {
    private XiaoMing xiaoMing;

    public Proxy(XiaoMing xiaoMing) {
        this.xiaoMing = xiaoMing;
    }

    @Override
    public void mai() {
        System.out.println("我是中介 看你买房开始");
        xiaoMing.mai();
        System.out.println("我是中介 看你买房结束");
    }

    public static void main(String[] args) {
        //Hose proxy = new Proxy(new XiaoMing());
        //proxy.mai();
        String pattern = "[^\\u4e00-\\u9fa5]";

        String name = "阿金\uD83D\uDC95";

        System.out.println(name.replaceAll(pattern, ""));

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        // List<String> strings = list.subList(3, 5);
        // System.out.println(strings);

        ListIterator<String> stringListIterator = list.listIterator(3);
        /*while(stringListIterator.hasNext()) {
            System.out.println(stringListIterator.next());
        }*/
        if ("modify".equals(null)) {
            System.out.println("11111111111111");
        } else {
            System.out.println("22222222222222");
        }

        File file = new File("");
    }
}
