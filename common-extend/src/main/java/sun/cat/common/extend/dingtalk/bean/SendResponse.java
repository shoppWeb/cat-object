package sun.cat.common.extend.dingtalk.bean;


import sun.cat.common.extend.dingtalk.bean.common.Err;

/**
 * 发送企业消息返回信息
 * Created by 小小工作者 on 2016/4/22.
 */
public class SendResponse extends Err {
    //如果收件人、部门或标签不存在，发送仍然执行，但返回无效的部分
    private String invaliduser;//无效的收件人
    private String invalidparty;//无效的部门

    public String getInvaliduser() {
        return invaliduser;
    }

    public void setInvaliduser(String invaliduser) {
        this.invaliduser = invaliduser;
    }

    public String getInvalidparty() {
        return invalidparty;
    }

    public void setInvalidparty(String invalidparty) {
        this.invalidparty = invalidparty;
    }
}
