package sun.cat.common.extend.dingtalk.api;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import sun.cat.common.extend.dingtalk.bean.JsApiTicket;
import sun.cat.common.extend.dingtalk.client.LocalHttpClient;

import java.nio.charset.Charset;

/**
 * AccessToken/SsoToken管理
 */
public class JsApiTickenAPI extends BaseAPI {


	public static JsApiTicket getJsApiTicket(String accessToken){
		HttpUriRequest httpUriRequest = RequestBuilder.get()
				.setUri(BASE_URI + "/get_jsapi_ticket")
				.addParameter("access_token", accessToken)//c4649c04a28b32b9a6fcb34e88c3d989
				.setEntity(new StringEntity("{\"type\":\"jsapi\"}", Charset.forName("utf-8")))
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest, JsApiTicket.class);
	}


}
