package sun.cat.common.extend.dingtalk.bean.common;

import java.util.List;

/**
 * Created by 小小工作者 on 2016/4/21.
 */
public class UserList extends Err{
    private boolean hasMore;//在分页查询时返回，代表是否还有下一页更多数据
    private List<UserInfo> userlist;//成员列表

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public List<UserInfo> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<UserInfo> userlist) {
        this.userlist = userlist;
    }
}
