package sun.cat.common.extend.dingtalk.bean.chat.message;


import sun.cat.common.extend.dingtalk.bean.Message.OAMessage;

/**
 * 发送消息到群会话 文件消息
 * Created by 小小工作者 on 2016/4/21.
 */
@Deprecated
public class ChatOAMessage extends OAMessage {

    private String chatid;//群会话的id
    private String sender;//发送者的userid

    public ChatOAMessage(OAMessage.OA oa) {
        super(oa);
    }


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
