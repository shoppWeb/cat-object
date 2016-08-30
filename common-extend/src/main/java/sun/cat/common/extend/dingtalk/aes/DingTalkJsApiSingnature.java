package sun.cat.common.extend.dingtalk.aes;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

/**
 * 钉钉jsapi签名工具类
 *  Created by 小小工作者 on 2016/4/22.
 */
public class DingTalkJsApiSingnature {
    /**
     * 获取jsapi签名
     * @param url //当前网页的URL，不包含#及其后面部分
     * @param nonce //随机字符串，自己随便填写即可
     * @param timeStamp //当前时间戳，具体值为当前时间到1970年1月1号的秒数
     * @param jsTicket
     * @return
     * @throws DingTalkEncryptException
     */
    public static String getJsApiSingnature(String url,String nonce,Long timeStamp,String jsTicket) throws DingTalkEncryptException{
        String plainTex = "jsapi_ticket=" + jsTicket +"&noncestr=" + nonce +"&timestamp=" + timeStamp + "&url=" + url;
        System.out.println(plainTex);
        String signature = "";
        try{
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(plainTex.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
            return signature;
        }catch (Exception e){
            throw new DingTalkEncryptException(DingTalkEncryptException.COMPUTE_SIGNATURE_ERROR);
        }
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash){
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }


    /**
     * 签名生成 测试
     * @param args
     * @throws Exception
     */
//    public static void main(String args[]) throws Exception{
//        String url="http://sunhailong.ittun.com/view/app/sign.html";
//        String nonce="changzheng";
//        Long timeStamp = System.currentTimeMillis();
//        String tikcet="993UQSsH7AdQYa6K38J98xgS9e7qYLROHjHkPjFWtNW5DjcQ5ZNs3Bxtksve1OZPF6sdwBQgM3OJvKWDQDmbGV";
//        System.err.println(getJsApiSingnature(url,nonce,timeStamp,tikcet));
//    }
}