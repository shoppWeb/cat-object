package sun.cat.common.extend.dingtalk.bean.common;

/**
 * 企业设置的微应用可见范围
 * Created by 小小工作者 on 2016/4/21.
 */
public class MicroappVisibleScopes extends Err {
    private int[] deptVisibleScopes;//	查询的该微应用的可见部门列表
    private String[] userVisibleScopes;//查询的该微应用的可见用户列表


    public int[] getDeptVisibleScopes() {
        return deptVisibleScopes;
    }

    public void setDeptVisibleScopes(int[] deptVisibleScopes) {
        this.deptVisibleScopes = deptVisibleScopes;
    }

    public String[] getUserVisibleScopes() {
        return userVisibleScopes;
    }

    public void setUserVisibleScopes(String[] userVisibleScopes) {
        this.userVisibleScopes = userVisibleScopes;
    }
}
