package sun.cat.common.extend.dingtalk.bean.common;

/**
 * 部门
 * Created by 小小工作者 on 2016/4/21.
 */
public class DepartmentInfo extends Err{
    private int id;//部门id
    private String name;//部门名称
    private int parentid;//父部门id，根部门为1
    private boolean createDeptGroup;//	是否同步创建一个关联此部门的企业群, true表示是, false表示不是
    private boolean autoAddUser;//当群已经创建后，是否有新人加入部门会自动加入该群, true表示是, false表示不是
    private boolean deptHiding;//是否隐藏部门, true表示隐藏, false表示显示
    private String deptPerimits;//可以查看指定隐藏部门的其他部门列表，如果部门隐藏，则此值生效，取值为其他的部门id组成的的字符串，使用’ | ‘符号进行分割
    private String userPerimits;//可以查看指定隐藏部门的其他人员列表，如果部门隐藏，则此值生效，取值为其他的人员userid组成的的字符串，使用’ | '符号进行分割
    private boolean outerDept;//是否本部门的员工仅可见员工自己, 为true时，本部门员工默认只能看到员工自己
    private String outerPermitDepts;//本部门的员工仅可见员工自己为true时，可以配置额外可见部门，值为部门id组成的的字符串，使用’ | '符号进行分割
    private String outerPermitUsers;//本部门的员工仅可见员工自己为true时，可以配置额外可见人员，值为userid组成的的字符串，使用’ | '符号进行分割
    private String orgDeptOwner;//企业群群主
    private String deptManagerUseridList;//部门的主管列表,取值为由主管的userid组成的字符串，不同的userid使用’ | '符号进行分割


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public boolean isCreateDeptGroup() {
        return createDeptGroup;
    }

    public void setCreateDeptGroup(boolean createDeptGroup) {
        this.createDeptGroup = createDeptGroup;
    }

    public boolean isAutoAddUser() {
        return autoAddUser;
    }

    public void setAutoAddUser(boolean autoAddUser) {
        this.autoAddUser = autoAddUser;
    }

    public boolean isDeptHiding() {
        return deptHiding;
    }

    public void setDeptHiding(boolean deptHiding) {
        this.deptHiding = deptHiding;
    }

    public String getDeptPerimits() {
        return deptPerimits;
    }

    public void setDeptPerimits(String deptPerimits) {
        this.deptPerimits = deptPerimits;
    }

    public String getUserPerimits() {
        return userPerimits;
    }

    public void setUserPerimits(String userPerimits) {
        this.userPerimits = userPerimits;
    }

    public boolean isOuterDept() {
        return outerDept;
    }

    public void setOuterDept(boolean outerDept) {
        this.outerDept = outerDept;
    }

    public String getOuterPermitDepts() {
        return outerPermitDepts;
    }

    public void setOuterPermitDepts(String outerPermitDepts) {
        this.outerPermitDepts = outerPermitDepts;
    }

    public String getOuterPermitUsers() {
        return outerPermitUsers;
    }

    public void setOuterPermitUsers(String outerPermitUsers) {
        this.outerPermitUsers = outerPermitUsers;
    }

    public String getOrgDeptOwner() {
        return orgDeptOwner;
    }

    public void setOrgDeptOwner(String orgDeptOwner) {
        this.orgDeptOwner = orgDeptOwner;
    }

    public String getDeptManagerUseridList() {
        return deptManagerUseridList;
    }

    public void setDeptManagerUseridList(String deptManagerUseridList) {
        this.deptManagerUseridList = deptManagerUseridList;
    }
}
