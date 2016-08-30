package sun.cat.common.extend.dingtalk.bean.Message;

/**
 * 消息
 * Created by 小小工作者 on 2016/4/21.
 */
public abstract class Message {
    private String msgtype;//消息类型


    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public Message() {
        super();
    }

    public Message( String msgtype) {
        this.msgtype = msgtype;
    }
}
