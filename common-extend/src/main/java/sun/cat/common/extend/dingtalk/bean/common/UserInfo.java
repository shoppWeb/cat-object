package sun.cat.common.extend.dingtalk.bean.common;

import com.alibaba.fastjson.JSONObject;

/**
 * 人员信息
 * Created by 小小工作者 on 2016/4/21.
 */
public class UserInfo extends Err {
    private String userid;//员工唯一标识ID（不可修改），企业内必须唯一。长度为1~64个字符，如果不传，服务器将自动生成一个userid
    private String name;//成员名称。长度为1~64个字符
    private JSONObject orderInDepts;//在对应的部门中的排序, Map结构的json字符串, key是部门的Id, value是人员在这个部门的排序值
    private int[] department;//	数组类型，数组里面值为整型，成员所属部门id列表
    private String position;//职位信息。长度为0~64个字符
    private String mobile;//手机号码。企业内必须唯一
    private String tel;//分机号，长度为0~50个字符
    private String workPlace;//办公地点，长度为0~50个字符
    private String remark;//备注，长度为0~1000个字符
    private String email;//邮箱。长度为0~64个字符。企业内必须唯一
    private String jobnumber;//	员工工号。对应显示到OA后台和客户端个人资料的工号栏目。长度为0~64个字符
    private boolean isHide;//是否号码隐藏, true表示隐藏, false表示不隐藏。隐藏手机号后，手机号在个人资料页隐藏，但仍可对其发DING、发起钉钉免费商务电话。
    private boolean isSenior;//是否高管模式，true表示是，false表示不是。开启后，手机号码对所有员工隐藏。普通员工无法对其发DING、发起钉钉免费商务电话。高管之间不受影响。
    private JSONObject extattr;//扩展属性，可以设置多种属性(但手机上最多只能显示10个扩展属性，具体显示哪些属性，请到OA管理后台->设置->通讯录信息设置和OA管理后台->设置->手机端显示信息设置)

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JSONObject getOrderInDepts() {
        return orderInDepts;
    }

    public void setOrderInDepts(JSONObject orderInDepts) {
        this.orderInDepts = orderInDepts;
    }

    public int[] getDepartment() {
        return department;
    }

    public void setDepartment(int[] department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobnumber() {
        return jobnumber;
    }

    public void setJobnumber(String jobnumber) {
        this.jobnumber = jobnumber;
    }

    public boolean isHide() {
        return isHide;
    }

    public void setHide(boolean hide) {
        isHide = hide;
    }

    public boolean isSenior() {
        return isSenior;
    }

    public void setSenior(boolean senior) {
        isSenior = senior;
    }

    public JSONObject getExtattr() {
        return extattr;
    }

    public void setExtattr(JSONObject extattr) {
        this.extattr = extattr;
    }
}
