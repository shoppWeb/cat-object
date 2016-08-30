package sun.cat.common.extend.dingtalk.bean;


import sun.cat.common.extend.dingtalk.bean.common.Err;

/**
 * jsapi_ticket
 * Created by 小小工作者 on 2016/4/22.
 */
public class JsApiTicket extends Err {

    private String ticket;//用于JS API的临时票据
    private int expires_in;//票据过期时间

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}
