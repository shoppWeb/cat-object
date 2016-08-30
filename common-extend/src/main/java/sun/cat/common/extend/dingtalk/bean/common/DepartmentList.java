package sun.cat.common.extend.dingtalk.bean.common;

import java.util.List;

/**
 * 部门列表
 * Created by 小小工作者 on 2016/4/21.
 */
public class DepartmentList extends Err{

    private List<DepartmentInfo> department;

    public List<DepartmentInfo> getDepartment() {
        return department;
    }

    public void setDepartment(List<DepartmentInfo> department) {
        this.department = department;
    }
}
