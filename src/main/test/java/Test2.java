import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: huangzhb
 * @Date: 2019年05月08日 18:22:06
 * @Description:
 */
public class Test2 {
    public static void main(String[] args) {


        /*try {
            int i = 1 / 0;
        } catch(Exception e) {

            System.out.println("==============catch");
            int i = 1 / 0;
            throw e;
        } finally {

            System.out.println("==============finally");
        }*/
    }

    /**
     * String date = "11:20:30";
     * String matcher = getMatcher("\\d+:\\d+", date);
     * System.out.println(matcher);
     * @param regex
     * @param source
     * @return
     */
    public static String getMatcher(String regex, String source) {
        String result = "";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            result = matcher.group();
        }
        return result;
    }
}
