package sun.cat.common.extend.dingtalk.bean.chat;

/**
 * 修改会话
 * Created by 小小工作者 on 2016/4/21.
 */
public class ChatUpdateRequest {

    private String name ;//群名称。长度限制为1~20个字符，不传则不修改
    private String owner;//	群主userId，员工唯一标识ID；必须为该会话成员之一；不传则不修改
    private String chatid;//群会话的id
    private String[] add_useridlist;//添加成员列表，每次最多操作40人，群人数上限为1000
    private String[] del_useridlist;//	删除成员列表，每次最多操作40人，群人数上限为1000

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

    public String getChatid() {
        return chatid;
    }

    public void setChatid(String chatid) {
        this.chatid = chatid;
    }

    public String[] getAdd_useridlist() {
        return add_useridlist;
    }

    public void setAdd_useridlist(String[] add_useridlist) {
        this.add_useridlist = add_useridlist;
    }

    public String[] getDel_useridlist() {
        return del_useridlist;
    }

    public void setDel_useridlist(String[] del_useridlist) {
        this.del_useridlist = del_useridlist;
    }
}
