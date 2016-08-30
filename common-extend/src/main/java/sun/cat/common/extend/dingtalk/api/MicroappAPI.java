package sun.cat.common.extend.dingtalk.api;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import sun.cat.common.extend.dingtalk.bean.common.Microapp;
import sun.cat.common.extend.dingtalk.bean.common.MicroappCreateResponse;
import sun.cat.common.extend.dingtalk.bean.common.MicroappVisibleScopes;
import sun.cat.common.extend.dingtalk.client.LocalHttpClient;
import sun.cat.common.extend.dingtalk.util.JsonUtil;

import java.nio.charset.Charset;

/**
 * 微应用管理
 * Created by 小小工作者 on 2016/4/21.
 */
public class MicroappAPI extends BaseAPI {


    /**
     * 创建微应用
     * @param accessToken
     * @param microappInfoJson
     * {
    "appIcon": "@HIdsabikkhjsdsas",
    "appName": "测试微应用",
    "appDesc": "测试使用的微应用",
    "homepageUrl": "http://oa.dingtalk.com/?h5",
    "pcHomepageUrl": "http://oa.dingtalk.com/?pc",
    "ompLink": ""
    }
     * @return
     */
    public static MicroappCreateResponse createMicroapp(String accessToken , String microappInfoJson){
        HttpUriRequest httpUriRequest = RequestBuilder.get()
                .setUri(BASE_URI + "/microapp/create")
                .addParameter("access_token", accessToken)
                .setEntity(new StringEntity(microappInfoJson, Charset.forName("utf-8")))
                .build();
        return LocalHttpClient.executeJsonResult(httpUriRequest, MicroappCreateResponse.class);
    }
    public static MicroappCreateResponse createMicroapp(String accessToken , Microapp microapp){
        return createMicroapp(accessToken , JsonUtil.toJSONString(microapp));
    }



    /**
     * 获取企业设置的微应用可见范围
     * @param accessToken
     * @param agentId  	需要查询询的微应用agentId
     * @return
     */
    public static MicroappVisibleScopes microappVisibleScopes(String accessToken , long agentId){
        HttpUriRequest httpUriRequest = RequestBuilder.get()
                .setUri(BASE_URI + "/microapp/visible_scopes")
                .addParameter("access_token", accessToken)
                .setEntity(new StringEntity("{  \"agentId\": \""+agentId+"\"}", Charset.forName("utf-8")))
                .build();
        return LocalHttpClient.executeJsonResult(httpUriRequest, MicroappVisibleScopes.class);
    }







}
