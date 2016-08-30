package sun.cat.common.extend.dingtalk.bean;


import sun.cat.common.extend.dingtalk.bean.common.Err;

/**
 * 通过CODE换取用户身份 返回信息
 * Created by 小小工作者 on 2016/4/22.
 */
public class AvoidLandingResponse extends Err {
    private String userid;//	员工在企业内的UserID
    private String deviceId;//	手机设备号,由钉钉在安装时随机产生
    private boolean is_sys;//	是否是管理员
    private int sys_level;//级别，三种取值。0:非管理员 1：普通管理员 2：超级管理员



    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public boolean is_sys() {
        return is_sys;
    }

    public void setIs_sys(boolean is_sys) {
        this.is_sys = is_sys;
    }

    public int getSys_level() {
        return sys_level;
    }

    public void setSys_level(int sys_level) {
        this.sys_level = sys_level;
    }
}
