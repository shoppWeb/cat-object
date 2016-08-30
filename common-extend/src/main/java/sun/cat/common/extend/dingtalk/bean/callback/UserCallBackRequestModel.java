package sun.cat.common.extend.dingtalk.bean.callback;

import java.sql.Timestamp;

/**
 * Created by 小小工作者 on 2016/5/17.
 */
public class UserCallBackRequestModel {

    private String EventType;
    private Timestamp TimeStamp;
    private String[] UserId;
    private String CorpId;

    public String getCorpId() {
        return CorpId;
    }

    public void setCorpId(String corpId) {
        CorpId = corpId;
    }

    public String getEventType() {
        return EventType;
    }

    public void setEventType(String eventType) {
        EventType = eventType;
    }

    public Timestamp getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        TimeStamp = timeStamp;
    }

    public String[] getUserId() {
        return UserId;
    }

    public void setUserId(String[] userId) {
        UserId = userId;
    }
}
