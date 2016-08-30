package sun.cat.common.extend.dingtalk.bean.chat;


import sun.cat.common.extend.dingtalk.bean.common.Err;

/**
 * 获取会话
 * Created by 小小工作者 on 2016/4/21.
 */
public class ChatGetResponse extends Err {

    private ChatInfo chatInfo;//	群会话信息

    public class ChatInfo{
        private String name ;//群名称
        private String owner;//群主userid
        private String[] useridlist;//群成员userId列表
        private String[] agentidlist;//群绑定的微应用agentId列表

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

        public String[] getAgentidlist() {
            return agentidlist;
        }

        public void setAgentidlist(String[] agentidlist) {
            this.agentidlist = agentidlist;
        }
    }

    public ChatInfo getChatInfo() {
        return chatInfo;
    }

    public void setChatInfo(ChatInfo chatInfo) {
        this.chatInfo = chatInfo;
    }
}
