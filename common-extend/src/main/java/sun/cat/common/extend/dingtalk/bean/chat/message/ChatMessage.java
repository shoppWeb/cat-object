package sun.cat.common.extend.dingtalk.bean.chat.message;

/**
 * 群会话消息
 * Created by 小小工作者 on 2016/4/21.
 */
@Deprecated
public abstract class ChatMessage {
    private String chatid;//群会话的id
    private String sender;//发送者的userid
    private String msgtype;//消息类型

    public String getChatid() {
        return chatid;
    }

    public void setChatid(String chatid) {
        this.chatid = chatid;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public ChatMessage() {
        super();
    }

    public ChatMessage(String chatid, String sender, String msgtype) {
        this.chatid = chatid;
        this.sender = sender;
        this.msgtype = msgtype;
    }
}
