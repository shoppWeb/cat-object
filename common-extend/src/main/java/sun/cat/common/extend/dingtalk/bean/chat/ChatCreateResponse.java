package sun.cat.common.extend.dingtalk.bean.chat;


import sun.cat.common.extend.dingtalk.bean.common.Err;

/**
 * 创建会话
 * Created by 小小工作者 on 2016/4/21.
 */
public class ChatCreateResponse extends Err {

    private String chatid;//群会话的id

    public String getChatid() {
        return chatid;
    }

    public void setChatid(String chatid) {
        this.chatid = chatid;
    }
}
