package com.bascker.wechat.util;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * WeChat Utils
 *
 * @author bascker
 */
public class WechatUtils {

    /**
     * 微信 GET 请求的加密/校验流程
     * 1.将 token、timestamp、nonce 三个参数进行字典序排序
     * 2.将三个参数字符串拼接成一个字符串进行 sha1 加密
     * 3.加密后的字符串可与 signature 对比，标识该请求来源于微信
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignature(final String signature, final String token, final String timestamp, final String nonce){
        final String[] arr = new String[]{token, timestamp, nonce};
        Arrays.sort(arr);

        final StringBuffer buff = new StringBuffer();
        for(int i = 0; i < arr.length; i ++){
            buff.append(arr[i]);
        }

        // 3. 将通过 sha1 加密后生成的字符串与微信传递过来的签名相比较
        final String sha1Encrypt = sha1Encrypt (buff.toString());
        return sha1Encrypt.equals(signature);
    }

    /**
     * SHA1 加密
     * @param str
     * @return
     */
    public static String sha1Encrypt(final String str){
        if (str == null || str.length() == 0){
            return null;
        }

        final char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            final MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(str.getBytes("UTF-8"));

            final byte[] md = messageDigest.digest();
            int j = md.length;
            final char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i ++) {
                final byte b = md[i];
                buf[k ++] = hexDigits[b >>> 4 & 0xf];
                buf[k ++] = hexDigits[b & 0xf];
            }

            return new String(buf);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private WechatUtils(){}

}
