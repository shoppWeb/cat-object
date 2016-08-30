package sun.cat.common.extend.dingtalk.bean.chat.message;


import sun.cat.common.extend.dingtalk.bean.Message.VoiceMessage;

/**
 * 发送消息到群会话 音频消息
 * Created by 小小工作者 on 2016/4/21.
 */
@Deprecated
public class ChatVoiceMessage extends VoiceMessage {

    private String chatid;//群会话的id
    private String sender;//发送者的userid

    public ChatVoiceMessage(String mediaId, String duration) {
        super(mediaId, duration);
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
