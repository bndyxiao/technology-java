package com.technology.util.security.md;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD4Digest;
import org.bouncycastle.crypto.digests.MD5Digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author: huangzhb
 * @Date: 2019年02月01日 14:14:11
 * @Description:
 */
public class ImoocMD {

    private static String src = "imooc security md";

    public static void main(String[] args) {
        jdkMD5();
        jdkMD2();
        bcMd4();
        bcMd5();
    }

    public static void jdkMD5() {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] md5Bytes = md.digest(src.getBytes());
            System.out.println("JDK MD5:" + Hex.encodeHexString(md5Bytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static void jdkMD2() {
        try {
            MessageDigest md = MessageDigest.getInstance("MD2");

            byte[] md2Bytes = md.digest(src.getBytes());
            System.out.println("JDK MD2:" + Hex.encodeHexString(md2Bytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static void bcMd4() {
        Digest digest = new MD4Digest();
        digest.update(src.getBytes(), 0, src.getBytes().length);
        byte[] md4Bytes = new byte[digest.getDigestSize()];
        digest.doFinal(md4Bytes, 0);
        System.out.println("BC MD4:" + org.bouncycastle.util.encoders.Hex.toHexString(md4Bytes));
    }

    public static void bcMd5() {
        Digest digest = new MD5Digest();
        digest.update(src.getBytes(), 0, src.getBytes().length);
        byte[] md5Bytes = new byte[digest.getDigestSize()];
        digest.doFinal(md5Bytes, 0);
        System.out.println("BC MD4:" + org.bouncycastle.util.encoders.Hex.toHexString(md5Bytes));
    }

    public static void ccMd5() {
        String md5 = DigestUtils.md5Hex(src.getBytes());
        System.out.println("CC MD5:" + md5);
    }

}
