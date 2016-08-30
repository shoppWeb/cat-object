package sun.cat.common.extend.dingtalk.api;

import com.alibaba.fastjson.JSON;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.junit.Test;
import sun.cat.common.extend.dingtalk.bean.common.AccessToken;
import sun.cat.common.extend.dingtalk.bean.common.SsoToken;
import sun.cat.common.extend.dingtalk.client.LocalHttpClient;


/**
 * AccessToken/SsoToken管理
 */
public class TokenAPI extends BaseAPI {

	/**
	 * 获取access_token
	 * @param corpid  企业Id
	 * @param corpsecret  企业应用的凭证密钥
	 * @return
	 */
	public static AccessToken accessToken(String corpid, String corpsecret){
		HttpUriRequest httpUriRequest = RequestBuilder.get()
				.setUri(BASE_URI + "/gettoken")
				.addParameter("corpid", corpid)//ding3dfd0f1168a40f72
				.addParameter("corpsecret", corpsecret)//iFEHb5isU4y_EN7_8ncijt-KOf4MkcMbZYtQ19wmbxzZk_y4eKLUFj_1zzL0MhIe
				.build();
		//Content-Type:application/json;charset=UTF-8
		return LocalHttpClient.executeJsonResult(httpUriRequest, AccessToken.class);
	}

	/**
	 * 获取SsoToken
	 * @param corpid  企业Id
	 * @param corpsecret  这里必须填写专属的SSOSecret
	 * @return
	 */
	public static SsoToken ssoToken(String corpid, String corpsecret){
		HttpUriRequest httpUriRequest = RequestBuilder.get()
				.setUri(BASE_URI + "/sso/gettoken")
				.addParameter("corpid", corpid)//ding3dfd0f1168a40f72
				.addParameter("corpsecret", corpsecret)//kP-_MEAoBtAXkEKjA2q0orteFWnepWHdP_Ifxeofxo3FeTlrSzdRUx47HCC-FXc9
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest, SsoToken.class);
	}

	@Test
	public void test(){
		AccessToken token = TokenAPI.accessToken("ding3dfd0f1168a40f72" , "iFEHb5isU4y_EN7_8ncijt-KOf4MkcMbZYtQ19wmbxzZk_y4eKLUFj_1zzL0MhIe");
		System.err.print(JSON.toJSONString(token));//c4649c04a28b32b9a6fcb34e88c3d989
	}

}
