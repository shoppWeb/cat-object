package sun.cat.common.extend.dingtalk.api;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import sun.cat.common.extend.dingtalk.bean.Message.Message;
import sun.cat.common.extend.dingtalk.bean.SendResponse;
import sun.cat.common.extend.dingtalk.bean.SendToConversationResponse;
import sun.cat.common.extend.dingtalk.client.LocalHttpClient;
import sun.cat.common.extend.dingtalk.util.JsonUtil;

import java.nio.charset.Charset;

/**
 * 消息API
 * Created by 小小工作者 on 2016/4/22.
 */
public class MessageAPI extends BaseAPI {

    /**
     *  发送普通会话消息
     * @param accessToken
     * @param message
     * @param sender
     * @param cid
     * @return
     */
    public static SendToConversationResponse sendToConversation(String accessToken , Message message , String sender , String cid){

        JSONObject jsonObject = JSONObject.parseObject(JsonUtil.toJSONString(message));
        jsonObject.put("sender" , sender);
        jsonObject.put("cid" , cid);

        HttpUriRequest httpUriRequest = RequestBuilder.post()
                .setUri(BASE_URI + "/message/send_to_conversation")
                .addParameter("access_token", accessToken)
                .setEntity(new StringEntity(JsonUtil.toJSONString(jsonObject) , Charset.forName("utf-8")))
                .build();
        return LocalHttpClient.executeJsonResult(httpUriRequest, SendToConversationResponse.class);
    }




    //发送企业会话消息

    //https://oapi.dingtalk.com/message/send?access_token=ACCESS_TOKEN

    /**
     *
     * @param accessToken
     * @param message
     * @param touser 员工ID列表（消息接收者，多个接收者用’ | '分隔）。特殊情况：指定为@all，则向该企业应用的全部成员发送
     * @param toparty 部门id列表，多个接收者用’ | '分隔。当touser为@all时忽略本参数 touser或者toparty 二者有一个必填
     * @param agentid 企业应用id，这个值代表以哪个应用的名义发送消息
     * @return
     */
    public static SendResponse sendToConversation(String accessToken , Message message , String touser , String toparty , String agentid){

        JSONObject jsonObject = JSONObject.parseObject(JsonUtil.toJSONString(message));
        jsonObject.put("toparty" , toparty);
        jsonObject.put("touser" , touser);
        jsonObject.put("agentid" , agentid);
        HttpUriRequest httpUriRequest = RequestBuilder.post()
                .setUri(BASE_URI + "/message/send")
                .addParameter("access_token", accessToken)
                .setEntity(new StringEntity(JsonUtil.toJSONString(jsonObject) , Charset.forName("utf-8")))
                .build();
        return LocalHttpClient.executeJsonResult(httpUriRequest, SendResponse.class);
    }



}
