package sun.cat.common.extend.dingtalk.bean.chat.message;


import sun.cat.common.extend.dingtalk.bean.Message.Message;

/**
 * 发送消息到群会话 link消息
 * Created by 小小工作者 on 2016/4/21.
 */
@Deprecated
public class ChatLinkMessage extends Message {

    private String chatid;//群会话的id
    private String sender;//发送者的userid

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
}
