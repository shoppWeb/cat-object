package sun.cat.common.extend.dingtalk.model;


import sun.cat.utils.properties.PropertiesUtil;

/**
 * Created by 小小工作者 on 2016/5/10.
 */
public class DingTalkViewCredentialModel {

    private String url;
    private Long timeStamp;
    private String tikcet;
    private String agentId = PropertiesUtil.getPropertyValue("conf/conf.properties", "dingtalk.agentId");
    private String corpId = PropertiesUtil.getPropertyValue("conf/conf.properties", "dingtalk.corpid");
    private final String nonce="changzheng";


    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getNonce() {
        return nonce;
    }

    public String getTikcet() {
        return tikcet;
    }

    public void setTikcet(String tikcet) {
        this.tikcet = tikcet;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
