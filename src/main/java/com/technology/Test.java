
public class Test {
    public static void main(String[] args) {

        System.out.println("123456789".substring(0,8));

        /*String str1 = "hello2";
        final String finalStr = "hello";
        String b = finalStr + "2";
        System.out.println(str1 == b);


        *//*final String str1 = "hello2";
        String finalStr = "hello";
        String b = finalStr + "2";
        System.out.println(str1 == b);*//*

        String c = "123";
        String d = "123";
        System.out.println(c == d);

        Runtime r = Runtime.getRuntime();
        r.gc();
        long startMem = r.freeMemory();
        // String key = "";
        for (int i = 0; i < 100; i++) {
            String key = "" + i;
            System.out.println(key);
        }

        long orz = startMem - r.freeMemory();
        System.out.println("========" + orz);*/

        System.out.println("" == null);

    }
}
