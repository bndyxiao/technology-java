import com.alibaba.fastjson.JSONObject;
import com.technology.util.StringUtils;
import jodd.util.StringUtil;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author: huangzhb
 * @Date: 2019年11月04日 16:05:12
 * @Description:
 */
public class FormatJson {

    public static void main(String[] args) throws IOException {


        /*List<String> list = null;
        for (String item : list) {
            System.out.println(item);
        }*/

        String s1 = "a";
        String s2 = "b";
        String s3 = "a" + "b";
        String s4 = s1 + s2;
        String s5 = "ab";
        String s6 = s4.intern();
        System.out.println(s3 == s4);
        System.out.println(s3 == s5);
        System.out.println(s3 == s6);

        String x1 = "cd";
        String x2 = new String("c") +new String("d");
        x2.intern();
        System.out.println(x1 == x2);

        /*BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("d:/new.txt"), "ISO-8859-1"));

        String s = null;

        while ((s = br.readLine()) != null) {
          String[] str =  s.split("\t");
            //System.out.println(new String(new String(new String(str[2].getBytes("ANSI")).getBytes("UTF-8"))));
            System.out.println(str[2]);
        }

        br.close();*/
//        String str = "qrscene_bbx01_福建省厦门市_黄梅宣传单_17671153805";

        //String str = new String("qrscene_bbx01_ç¦\u008Få»ºçœ\u0081åŽ¦é—¨å¸‚_é»„æ¢…å®£ä¼ å\u008D•_17671153805");

//        str = new String(str.getBytes("ISO-8859-1"));
//        System.out.println(str);
        // filterUnsubscribe();
        // toJsonStr();
        /*FileReader fr = new FileReader(new File("d:/subscribe.txt"));
        BufferedReader br = new BufferedReader(fr);
        BufferedWriter bw = new BufferedWriter(new FileWriter("d:/json2.sql"));
        String s = null;
        JSONObject item = null;
        StringBuffer buffer = new StringBuffer();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            while ((s = br.readLine()) != null) {
                try {
                    item = JSONObject.parseObject(s);
                } catch (Exception e) {
                    System.out.println(s);
                    continue;
                }
                buffer.append("INSERT INTO bbx_materiel_user_subscribe(openid, sceneid, qr_id, materiel_id, subscribe, unsubscribe, type, unnionid) VALUE (");
                if (StringUtil.isNotBlank(item.getString("FromUserName"))) {
                    buffer.append("\'").append(item.getString("FromUserName")).append("\',");
                } else {
                    buffer.append("null").append(",");
                }
                if (StringUtil.isNotBlank(item.getString("EventKey")) && !item.getString("EventKey").equals("null")) {
                    buffer.append("\'").append(item.getString("EventKey")).append("\',");

                    // 根据场景id补充qrId和materiel_id
                    String eventKey = item.getString("EventKey");
                    if (eventKey.startsWith("qrscene_materiel_")) {

                        buffer.append(eventKey.replaceAll("qrscene_materiel_", "").split("_")[1]).append(",");
                        buffer.append(eventKey.replaceAll("qrscene_materiel_", "").split("_")[0]).append(",");

                    } else {
                        buffer.append("null").append(",");
                        buffer.append("null").append(",");
                    }

                } else {
                    buffer.append("null").append(",");
                    buffer.append("null").append(",");
                    buffer.append("null").append(",");
                }
                *//*if (StringUtil.isNotBlank(item.getString("qrId")) && item.getString("qrId") != null) {
                    buffer.append("").append(item.getString("qrId")).append(",");
                } else {
                    buffer.append("null").append(",");
                }

                if (StringUtils.isNotBlank(item.getString("materielId")) && item.getString("materielId") != null) {
                    buffer.append(item.getString("materielId")).append(",");
                } else {
                    buffer.append("null").append(",");
                }*//*
                if (StringUtils.isNotBlank(item.getString("CreateTime")) && item.getString("CreateTime") != null) {
                    buffer.append("\'").append(simpleDateFormat.format(new Date(item.getLong("CreateTime") * 1000))).append("\',");
                } else {
                    buffer.append("null").append(",");
                }

                buffer.append("null").append(",");

                *//*if (StringUtils.isNotBlank(item.getString("unsubscribe")) && item.getString("unsubscribe") != null) {
                    buffer.append("\'").append(item.getString("unsubscribe")).append("\',");
                } else {
                    buffer.append("null").append(",");
                }*//*

                buffer.append("1").append(",");
                buffer.append("null").append(",");

                *//*if (StringUtils.isNotBlank(item.getString("type"))) {
                    buffer.append(item.getString("type")).append(",");
                } else {
                    buffer.append("null").append(",");
                }*//*
                *//*if (StringUtils.isNotBlank("unnionId") && item.getString("unnionId") != null) {
                    buffer.append("\'").append(item.getString("unnionId")).append("\',");
                } else {
                    buffer.append("null").append(",");
                }*//*

                buffer.append(");");
                bw.write(buffer.toString().replaceAll(",\\)", ")") + "\n");
                System.out.println(item.toJSONString());
                buffer.delete(0, buffer.length());
                // 构造sql
            }

        br.close();
        bw.close();*/
    }



    private static void filterUnsubscribe() throws IOException {
        FileReader fr = new FileReader(new File("d:/subscribe.txt"));
        BufferedReader br = new BufferedReader(fr);
        String s = null;
        StringBuffer buffer = new StringBuffer();
        BufferedWriter bw = new BufferedWriter(new FileWriter("d:/json1.txt"));
        JSONObject item = null;
        try {
            while ((s = br.readLine()) != null) {

                item = JSONObject.parseObject(s);
                if (item.getString("Event").equals("unsubscribe")) {
                    continue;
                }

                bw.write(s + "\n");
            }
        } catch (Exception e) {
            System.out.println(s);
            e.printStackTrace();
        }
        br.close();
        bw.close();
    }

    private static void toJsonStr() throws IOException {
        FileReader fr = new FileReader(new File("d:/subscribe.txt"));
        BufferedReader br = new BufferedReader(fr);
        String s = null;
        StringBuffer buffer = new StringBuffer();
        BufferedWriter bw = new BufferedWriter(new FileWriter("d:/json1.txt"));
        try {
            while ((s = br.readLine()) != null) {
                s = s.replaceAll(",", "\",");
                s = s.replaceAll("=", "\":\"");
                s = s.replaceAll("\\{", "{\"");
                s = s.replaceAll("\\}", "\"}");
                s = s.replaceAll("openid", "\"openid");
                s = s.replaceAll("sceneid", "\"sceneid");
                s = s.replaceAll("qrId", "\"qrId");
                s = s.replaceAll("materielId", "\"materielId");
                s = s.replaceAll("subscribe", "\"subscribe");
                s = s.replaceAll("unSubscribe", "\"unSubscribe");
                s = s.replaceAll("type", "\"type");
                s = s.replaceAll("\'", "");
                if (s.contains("unnionId")) {
                    s = s.replaceAll("unnionId", "\"unnionId");
                }

           /*s = s.replaceAll("id=null", "\"id\":\"null\"");
           s = s.replaceAll("openid=", "\"openid\":");
           s = s.replaceAll("sceneid=", "\"sceneid\":");
           s = s.replaceAll("qrId=", "\"qrId\":");
           s = s.replaceAll("\"qrId\":null", "\"qrId\":\"null\"");
           s = s.replaceAll()*/
                bw.write(s + "\n");
            }
        } catch (Exception e) {
            System.out.println(s);
            e.printStackTrace();
        }
        br.close();
        bw.close();
    }
}
