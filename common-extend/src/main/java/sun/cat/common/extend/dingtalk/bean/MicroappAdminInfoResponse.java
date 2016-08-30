package sun.cat.common.extend.dingtalk.bean;


import sun.cat.common.extend.dingtalk.bean.common.Err;

/**
 * 通过CODE换取微应用管理员的身份信息
 * Created by 小小工作者 on 2016/4/22.
 */
public class MicroappAdminInfoResponse extends Err {

    private boolean is_sys;//是否是管理员（在这里是true）
    private UserInfo user_info;//管理员信息

    public boolean is_sys() {
        return is_sys;
    }

    public void setIs_sys(boolean is_sys) {
        this.is_sys = is_sys;
    }

    public UserInfo getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfo user_info) {
        this.user_info = user_info;
    }

    public class CorpInfo{
        private String corp_name;//	公司名字
        private String corpid;//公司corpid

        public String getCorp_name() {
            return corp_name;
        }

        public void setCorp_name(String corp_name) {
            this.corp_name = corp_name;
        }

        public String getCorpid() {
            return corpid;
        }

        public void setCorpid(String corpid) {
            this.corpid = corpid;
        }
    }


    public class UserInfo{
        private String avatar;//头像地址
        private String email;//email地址”,
        private String name;//	用户名字,
        private String userid;//员工在企业内的UserID

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }
    }
}
