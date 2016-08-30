package sun.cat.common.extend.dingtalk.bean.callback;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 小小工作者 on 2016/5/17.
 */
public class UserCallBackResponseModel {

    private String  msg_signature = "111108bb8e6dbce3c9671d6fdb69d15066227608";//消息体签名
    private Timestamp timeStamp = new Timestamp(System.currentTimeMillis());//时间戳
    private String  nonce = "changzheng";//随机字符串
    private String  encrypt = "1ojQf0NSvw2WPvW7LijxS8UvISr8pdDP+rXpPbcLGOmIBNbWetRg7IP0vdhVgkVwSoZBJeQwY2zhROsJq/HJ+q6tp1qhl9L1+ccC9ZjKs1wV5bmA9NoAWQiZ+7MpzQVq+j74rJQljdVyBdI/dGOvsnBSCxCVW0ISWX0vn9lYTuuHSoaxwCGylH9xRhYHL9bRDskBc7bO0FseHQQasdfghjkl";//“success"加密字符串

    public String getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
    }

    public String getMsg_signature() {
        return msg_signature;
    }

    public void setMsg_signature(String msg_signature) {
        this.msg_signature = msg_signature;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }
}
