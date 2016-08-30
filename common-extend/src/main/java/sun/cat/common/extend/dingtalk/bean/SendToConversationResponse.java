package sun.cat.common.extend.dingtalk.bean;


import sun.cat.common.extend.dingtalk.bean.common.Err;

/**
 * 发送普通会话消息返回信息
 * Created by 小小工作者 on 2016/4/22.
 */
public class SendToConversationResponse extends Err {
    private String receiver;//如果是群，返回跟发送者同一家企业的一组工号；如果是个人聊天，只返回发送者同一家企业的一个工号；不在同一家企业，发送失败

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
