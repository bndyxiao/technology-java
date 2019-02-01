package com.technology.util.security.base64;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * @author: huangzhb
 * @Date: 2019年02月01日 13:40:45
 * @Description:
 */
public class ImoocBase64 {

    // 待加密明文
    private static String src = "imooc security base64";

    public static void main(String[] args) {
        // jdkBase64();
        // commonsCodecBase64();
        // bouncyCastleBase64();
    }

    /**
     * 使用jdk的base64
     */
    public static void jdkBase64(){
        BASE64Encoder encoder = new BASE64Encoder();
        String encode = encoder.encode(src.getBytes());
        System.out.println("encode:" + encode);

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            System.out.println("decode:" + new String(decoder.decodeBuffer(encode)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * commonsCodec base64
     */
    public static void commonsCodecBase64() {

        byte[] encodeBytes = Base64.encodeBase64(src.getBytes());
        System.out.println("encode:" + new String(encodeBytes));

        byte[] decodeBytes = Base64.decodeBase64(encodeBytes);
        System.out.println("decode:" + new String(decodeBytes));
    }

    /**
     * bc base64
     */
    public static void bouncyCastleBase64() {

        byte[] encodeBytes = org.bouncycastle.util.encoders.Base64.encode(src.getBytes());
        System.out.println("encode:" + new String(encodeBytes));

        byte[] decodeBytes = org.bouncycastle.util.encoders.Base64.decode(encodeBytes);
        System.out.println("decode:" + new String(decodeBytes));
    }
}
