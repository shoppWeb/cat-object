package sun.cat.common.extend.dingtalk.bean;


import sun.cat.common.extend.dingtalk.bean.common.Err;

/**
 * 回调bean
 * Created by 小小工作者 on 2016/4/22.
 */
public class CallBack extends Err {

    private String token;//加解密需要用到的token，ISV(服务提供商)推荐使用注册套件时填写的token，普通企业可以随机填写
    private String aes_key;//数据加密密钥。用于回调数据的加密，长度固定为43个字符，从a-z, A-Z, 0-9共62个字符中选取,您可以随机生成，ISV(服务提供商)推荐使用注册套件时填写的EncodingAESKey
    private String url;//接收事件回调的url
    private String[] call_back_tag;//需要监听的事件类型，有16种，"user_add_org”, “user_modify_org”, “user_leave_org”,“org_admin_add”, “org_admin_remove”, “org_dept_create”, “org_dept_modify”, “org_dept_remove”, “org_remove”, “chat_add_member”, “chat_remove_member”, “chat_quit”, “chat_update_owner”, “chat_update_title”, “chat_disband”, “chat_disband_microapp”


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAes_key() {
        return aes_key;
    }

    public void setAes_key(String aes_key) {
        this.aes_key = aes_key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String[] getCall_back_tag() {
        return call_back_tag;
    }

    public void setCall_back_tag(String[] call_back_tag) {
        this.call_back_tag = call_back_tag;
    }
}
