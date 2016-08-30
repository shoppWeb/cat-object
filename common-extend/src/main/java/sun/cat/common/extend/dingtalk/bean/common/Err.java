package sun.cat.common.extend.dingtalk.bean.common;

/**
 * Created by 小小工作者 on 2016/4/21.
 */
public class Err {
    private String errmsg;//错误信息
    private  int errcode;//	错误码
    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }
}
