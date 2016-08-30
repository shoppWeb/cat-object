package sun.cat.common.extend.dingtalk.api;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import sun.cat.common.extend.dingtalk.bean.AvoidLandingResponse;
import sun.cat.common.extend.dingtalk.bean.MicroappAdminInfoResponse;
import sun.cat.common.extend.dingtalk.client.LocalHttpClient;

import java.nio.charset.Charset;


/**
 * 免登陆API接口
 * Created by 小小工作者 on 2016/4/22.
 */
public class AvoidLandingAPI extends BaseAPI{

    /**
     * 通过CODE换取用户身份
     * @param accessToken
     * @param code 通过Oauth认证会给URL带上CODE
     * @return
     */
    public static AvoidLandingResponse getUserInfo(String accessToken , String code){
        HttpUriRequest httpUriRequest = RequestBuilder.get()
                .setUri(BASE_URI + "/user/getuserinfo")
                .addParameter("access_token", accessToken)
                .addParameter("code", code)
                .build();
        return LocalHttpClient.executeJsonResult(httpUriRequest, AvoidLandingResponse.class);
    }

    /**
     *
     * @param ssoToken 再次强调，此token不同于一般的accesstoken，需要调用获取微应用管理员免登需要的AccessToken
     * @param code 通过Oauth认证给URL带上的CODE
     * @return
     */
    public static MicroappAdminInfoResponse getMicroappAdminInfo(String ssoToken , String code){
        HttpUriRequest httpUriRequest = RequestBuilder.get()
                .setUri(BASE_URI + "/sso/getuserinfo")
                .addParameter("access_token", ssoToken)
                .addParameter("code", code)
                .build();
        return LocalHttpClient.executeJsonResult(httpUriRequest, MicroappAdminInfoResponse.class);
    }

}
