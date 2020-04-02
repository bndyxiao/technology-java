import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: huangzhb
 * @Date: 2019年05月11日 10:21:44
 * @Description:
 */
public class Test3 {
    public static void main(String[] args) {
        /*Double a1 = Double.valueOf("-48.94");
        Double a2 = Double.valueOf("50");
        System.out.println((a1 + a2) - (110/ 100) < 0);
        System.out.println(0.0 == 0);
        System.out.println(0.0 < 0);



        List<String> list = new ArrayList<>();
        list.add("150000 _to_150000 ");
        list.add("123_to_123");
        System.out.println(list.contains("150000 _to_150000 "));*/


        JSONObject param = new JSONObject();
        param.put("lineId", "1222");
        param.put("token", "");
        System.out.println(String.valueOf(param));
    }
}
