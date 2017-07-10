package com.project.common.util;

import java.security.MessageDigest;

/**
 * Created by qiaowentao on 2016/8/18.
 */
public class MD5 {

    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    /*private Object salt;
    private String algorithm;*/

    private MD5(/*Object salt, String algorithm*/) {
        /*this.salt = salt;
        this.algorithm = algorithm;*/
    }

    private static String encode(String rawPass,String salt) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            //加密后的字符串
            result = byteArrayToHexString(md.digest(mergePasswordAndSalt(rawPass,salt).getBytes("utf-8")));
        } catch (Exception ex) {
        }
        return result;
    }

    private static String mergePasswordAndSalt(String password,String salt) {
        if (password == null) {
            password = "";
        }

        if ((salt == null) || "".equals(salt)) {
            return password;
        } else {
            return password + "{" + salt.toString() + "}";
        }
    }

    /**
     * 转换字节数组为16进制字串
     * @param b 字节数组
     * @return 16进制字串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String md5Password(String salt,String password){

        String md5Password = encode(password,salt);
        return md5Password;
    }

    public static void main(String[] args) {
        String password = MD5.md5Password("RBMB2HdUKYmlQZxo","123456");
        System.out.println(password);
    }

}
