package sun.cat.utils;

/**
 * byte[]字节数组的转化
 * Created by 小小工作者 on 2015/10/21 0021.
 */
public class ByteUtils {
    public static byte[] longToByte(long number) {
        long temp = number;
        byte[] b = new byte[8];
        for (int i = 0; i < b.length; i++) {
            b[i] = new Long(temp & 0xff).byteValue();// 将最低位保存在最低位
            temp = temp >> 8; // 向右移8位
        }
        return b;
    }

    /**
     * int到byte[]
     * @param i
     * @return
     */
    public static byte[] intToByteArray(int i) {
        byte[] result = new byte[4];
        //由高位到低位
        result[0] = (byte)((i >> 24) & 0xFF);
        result[1] = (byte)((i >> 16) & 0xFF);
        result[2] = (byte)((i >> 8) & 0xFF);
        result[3] = (byte)(i & 0xFF);
        return result;
    }

    /**
     * byte[]转int
     * @param bytes
     * @return
     */
    public static int byteArrayToInt(byte[] bytes) {
        int value= 0;
        //由高位到低位
        for (int i = 0; i < 4; i++) {
            int shift= (4 - 1 - i) * 8;
            value +=(bytes[i] & 0x000000FF) << shift;//往高位游
        }
        return value;
    }


    public static byte[] intToByte(int number) {
        int temp = number;
        byte[] b = new byte[4];
        for (int i = 0; i < b.length; i++) {
            b[i] = new Integer(temp & 0xff).byteValue();// 将最低位保存在最低位
            temp = temp >> 4; // 向右移8位
        }
        return b;
    }


    public static int byteArrToInt(byte[] arr){
        int num = 0;
        for(int i = 0;i<arr.length;i++){
            num|=(arr[i] & 0xFF)<<(8*(arr.length-i-1));
        }
        return num;
    }
    public static short byteArrToShort(byte[] arr){
        short num = 0;
        for(int i = 0;i<arr.length;i++){
            num|=(short)(arr[i]& 0xFF)<<(8*(arr.length-i-1));
        }
        return num;
    }
    public static byte[] shortToByteArr(short num){
        int len = 2;
        byte[] bytes = new byte[len];
        for(int i = 0 ; i < len;i++){
            bytes[i] = (byte)((num>>(len-i-1)*8));
        }
        return bytes;
    }
    public static byte[] intToByteArr(int value){
        byte[] src = new byte[4];
        src[0] =  (byte) ((value>>24) & 0xFF);
        src[1] =  (byte) ((value>>16) & 0xFF);
        src[2] =  (byte) ((value>>8) & 0xFF);
        src[3] =  (byte) (value & 0xFF);
        return src;
    }
//    public static byte[] intToByteArr(int num,int len){
//        byte[] bytes = new byte[len];
//        for(int i = 0 ; i < len;i++){
//            bytes[i] = (byte)((num>>(len - i - 1) * 8));
//        }
//        return bytes;
//    }
    public static long byteArrToLong(byte[] arr){
        long num = 0;
        for(int i = 0;i<arr.length;i++){
            num|=(arr[i]& 0xFF)<<(8*(arr.length-i-1));
        }
        return num;
    }
    public static byte[] longToByteArr(long num){
        int len = 8;
        byte[] bytes = new byte[len];
        for(int i = 0 ; i < len;i++){
            bytes[i] = (byte)((num>>(len-i-1)*8));
        }
        return bytes;
    }

}
