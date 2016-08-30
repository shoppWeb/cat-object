package sun.cat.common.extend.dingtalk.bean.callback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 小小工作者 on 2016/5/17.
 */
public class RegisterCallBackModel {


    /**
     * access_token	String	是	调用接口凭证
     call_back_tag	Array[String]	是	需要监听的事件类型，有16种，"user_add_org”, “user_modify_org”, “user_leave_org”,“org_admin_add”, “org_admin_remove”, “org_dept_create”, “org_dept_modify”, “org_dept_remove”, “org_remove”, “chat_add_member”, “chat_remove_member”, “chat_quit”, “chat_update_owner”, “chat_update_title”, “chat_disband”, “chat_disband_microapp”
     token	String	是	加解密需要用到的token，ISV(服务提供商)推荐使用注册套件时填写的token，普通企业可以随机填写
     aes_key	String	是	数据加密密钥。用于回调数据的加密，长度固定为43个字符，从a-z, A-Z, 0-9共62个字符中选取,您可以随机生成，ISV(服务提供商)推荐使用注册套件时填写的EncodingAESKey
     url	String	是	接收事件回调的url
     */
    private List<String> call_back_tag;//需要监听的事件类型
    private String token = "changzheng";//加解密需要用到的token，ISV(服务提供商)推荐使用注册套件时填写的token，普通企业可以随机填写
    private String aes_key ="1234567890123456789012345678901234567890123";//数据加密密钥
    private String url;//接收事件回调的url


    public RegisterCallBackModel() {
    }

    public RegisterCallBackModel(String url) {
        this.url = url;
        this.call_back_tag = new ArrayList<>();
        this.call_back_tag.add("user_add_org");// 通讯录用户增加
        this.call_back_tag.add("user_modify_org");//通讯录用户更改
        this.call_back_tag.add("user_leave_org");//通讯录用户离职
        this.call_back_tag.add("org_admin_add");//通讯录用户被设为管理员
        this.call_back_tag.add("org_admin_remove");//通讯录用户被取消设置管理员
        this.call_back_tag.add("org_dept_create");// 通讯录企业部门创建
        this.call_back_tag.add("org_dept_modify");//通讯录企业部门修改
        this.call_back_tag.add("org_dept_remove");// 通讯录企业部门删除
        this.call_back_tag.add("org_remove");//企业被解散
//        this.call_back_tag.add("chat_add_member");
//        this.call_back_tag.add("chat_remove_member");
//        this.call_back_tag.add("chat_quit");
//        this.call_back_tag.add("chat_update_owner");
//        this.call_back_tag.add("chat_update_title");
//        this.call_back_tag.add("chat_disband");
//        this.call_back_tag.add("chat_disband_microapp");
    }

    public String getAes_key() {
        return aes_key;
    }

    public void setAes_key(String aes_key) {
        this.aes_key = aes_key;
    }

    public List<String> getCall_back_tag() {
        return call_back_tag;
    }

    public void setCall_back_tag(List<String> call_back_tag) {
        this.call_back_tag = call_back_tag;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
