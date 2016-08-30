package sun.cat.common.extend.dingtalk.api;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.junit.Test;
import sun.cat.common.extend.dingtalk.bean.callback.RegisterCallBackModel;
import sun.cat.common.extend.dingtalk.bean.chat.ChatCreateResponse;
import sun.cat.common.extend.dingtalk.bean.common.AccessToken;
import sun.cat.common.extend.dingtalk.bean.common.Err;
import sun.cat.common.extend.dingtalk.client.LocalHttpClient;
import sun.cat.common.extend.dingtalk.util.JsonUtil;

import java.nio.charset.Charset;

/**
 * 回调接口
 * Created by 小小工作者 on 2016/4/22.
 */
public class CallBackAPI extends BaseAPI{

    /**
     * 注册回调接口
     * @param registerCallBackModel
     * @param accessToken
     * @return
     */
    public static Err registerCallBack(RegisterCallBackModel registerCallBackModel , String accessToken){

        JSONObject jsonObject = JSONObject.parseObject(JsonUtil.toJSONString(registerCallBackModel));

        HttpUriRequest httpUriRequest = RequestBuilder.post()
                .setUri(BASE_URI + "/call_back/register_call_back")
                .addParameter("access_token", accessToken)
                .addHeader("Content-type" , "application/json")
                .setEntity(new StringEntity(JsonUtil.toJSONString(jsonObject), Charset.forName("utf-8")))
                .build();
        return LocalHttpClient.executeJsonResult(httpUriRequest, ChatCreateResponse.class);
    }


    @Test
    public void test(){
        RegisterCallBackModel registerCallBackModel = new RegisterCallBackModel("http://sunhailong.ittun.com/callBack/userCallBack");

        AccessToken accessToken = TokenAPI.accessToken("ding3dfd0f1168a40f72", "iFEHb5isU4y_EN7_8ncijt-KOf4MkcMbZYtQ19wmbxzZk_y4eKLUFj_1zzL0MhIe");

        Err err = CallBackAPI.registerCallBack(registerCallBackModel, accessToken.getAccess_token());
        System.out.println(JSONObject.toJSONString(err));
    }

}
