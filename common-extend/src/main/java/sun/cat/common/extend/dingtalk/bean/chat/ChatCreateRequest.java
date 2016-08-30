package sun.cat.common.extend.dingtalk.bean.chat;

/**
 * 创建会话
 * Created by 小小工作者 on 2016/4/21.
 */
public class ChatCreateRequest {
    private String name ;//群名称。长度限制为1~20个字符
    private String owner;//群主userId，员工唯一标识ID；必须为该会话useridlist的成员之一
    private String[] useridlist;//群成员列表，每次最多操作40人，群人数上限为1000


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String[] getUseridlist() {
        return useridlist;
    }

    public void setUseridlist(String[] useridlist) {
        this.useridlist = useridlist;
    }
}
