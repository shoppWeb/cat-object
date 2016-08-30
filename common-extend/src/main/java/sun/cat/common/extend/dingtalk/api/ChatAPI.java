package sun.cat.common.extend.dingtalk.api;

import com.alibaba.fastjson.JSONObject;
import sun.cat.common.extend.dingtalk.bean.Message.Message;
import sun.cat.common.extend.dingtalk.bean.chat.ChatCreateRequest;
import sun.cat.common.extend.dingtalk.bean.chat.ChatCreateResponse;
import sun.cat.common.extend.dingtalk.bean.chat.ChatGetResponse;
import sun.cat.common.extend.dingtalk.bean.chat.ChatUpdateRequest;
import sun.cat.common.extend.dingtalk.bean.common.Err;
import sun.cat.common.extend.dingtalk.client.LocalHttpClient;
import sun.cat.common.extend.dingtalk.util.JsonUtil;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;

import java.nio.charset.Charset;

/**
 * 群会话接口
 * Created by 小小工作者 on 2016/4/21.
 */
public class ChatAPI extends BaseAPI{

    /**
     * 创建会话
     * @param accessToken
     * @param chatCreateRequestJson
     * {
    "name": "群名称",
    "owner": "zhangsan",
    "useridlist": ["zhangsan","lisi"]
    }
     * @return
     */
    public static ChatCreateResponse createChat(String accessToken , String chatCreateRequestJson){
        HttpUriRequest httpUriRequest = RequestBuilder.post()
                .setUri(BASE_URI + "/chat/create")
                .addParameter("access_token", accessToken)
                .setEntity(new StringEntity(chatCreateRequestJson, Charset.forName("utf-8")))
                .build();
        return LocalHttpClient.executeJsonResult(httpUriRequest, ChatCreateResponse.class);
    }

    public static ChatCreateResponse createChat(String accessToken , ChatCreateRequest chatCreateRequest){
        return createChat(accessToken, JsonUtil.toJSONString(chatCreateRequest));
    }


    /**
     * 修改会话
     * @param accessToken
     * @param chatUpdateRequestJson
     * {
    "chatid": "chatxxxxxxxxxxxxxxxxxxx",
    "name": "群名称",
    "owner": "zhangsan",
    "add_useridlist": ["lisi"],
    "del_useridlist": ["wangwu"]
    }
     * @return
     */
    public static ChatCreateResponse updateChat(String accessToken , String chatUpdateRequestJson){
        HttpUriRequest httpUriRequest = RequestBuilder.post()
                .setUri(BASE_URI + "/chat/update")
                .addParameter("access_token", accessToken)
                .setEntity(new StringEntity(chatUpdateRequestJson, Charset.forName("utf-8")))
                .build();
        return LocalHttpClient.executeJsonResult(httpUriRequest, ChatCreateResponse.class);
    }
    public static ChatCreateResponse updateChat(String accessToken , ChatUpdateRequest chatUpdateRequest){
        return updateChat(accessToken, JsonUtil.toJSONString(chatUpdateRequest));
    }


    /**
     * 获取会话
     * @param accessToken
     * @param chatid 群会话的id
     * @return
     */
    public static ChatGetResponse getChat(String accessToken , String chatid){
        HttpUriRequest httpUriRequest = RequestBuilder.get()
                .setUri(BASE_URI + "/chat/get")
                .addParameter("access_token", accessToken)
                .setEntity(new StringEntity("{\"chatid\":\""+chatid+"\"}", Charset.forName("utf-8")))
                .build();
        return LocalHttpClient.executeJsonResult(httpUriRequest, ChatGetResponse.class);
    }

    /**
     * 绑定微应用和群会话 此接口仅限ISV接入使用
     * @param accessToken
     * @param chatid 群会话的id
     * @param agentid 	微应用agentId，每个群最多绑定5个微应用，一个群只能被一个ISV套件绑定一次
     * @return
     */
    public static Err bindChat(String accessToken , String chatid , String agentid){
        HttpUriRequest httpUriRequest = RequestBuilder.get()
                .setUri(BASE_URI + "/chat/bind")
                .addParameter("access_token", accessToken)
                .setEntity(new StringEntity("{\"chatid\":\""+chatid+"\",\"agentid\": \""+agentid+"\"}", Charset.forName("utf-8")))
                .build();
        return LocalHttpClient.executeJsonResult(httpUriRequest, Err.class);
    }

    /**
     * 解绑微应用和群会话  此接口仅限ISV接入使用
     * @param accessToken
     * @param chatid
     * @param agentid
     * @return
     */
    public static Err unbindChat(String accessToken , String chatid , String agentid){
        HttpUriRequest httpUriRequest = RequestBuilder.get()
                .setUri(BASE_URI + "/chat/unbind")
                .addParameter("access_token", accessToken)
                .setEntity(new StringEntity("{\"chatid\":\""+chatid+"\",\"agentid\": \""+agentid+"\"}", Charset.forName("utf-8")))
                .build();
        return LocalHttpClient.executeJsonResult(httpUriRequest, Err.class);
    }

    /**
     * 发送消息到群会话
     * @param accessToken
     * @param chatMessage
     * @return
     */
    public static Err sendChat(String accessToken , Message chatMessage , String chatid , String sender){

        JSONObject jsonObject = JSONObject.parseObject(JsonUtil.toJSONString(chatMessage));
        jsonObject.put("chatid" , chatid);
        jsonObject.put("sender" , sender);

        HttpUriRequest httpUriRequest = RequestBuilder.post()
                .setUri(BASE_URI + "/chat/send")
                .addParameter("access_token", accessToken)
                .setEntity(new StringEntity(JsonUtil.toJSONString(jsonObject) , Charset.forName("utf-8")))
                .build();
        return LocalHttpClient.executeJsonResult(httpUriRequest, Err.class);
    }

    //元素信息收集   手机店
    //数据查看汇总  xls导入  pc端

}
