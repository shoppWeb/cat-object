package sun.cat.common.extend.dingtalk.client;


import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import sun.cat.common.extend.dingtalk.util.JsonUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonResponseHandler{

	private static Map<String, ResponseHandler<?>> map = new HashMap<String, ResponseHandler<?>>();

	@SuppressWarnings("unchecked")
	public static <T> ResponseHandler<T> createResponseHandler(final Class<T> clazz){

		if(map.containsKey(clazz.getName())){
			return (ResponseHandler<T>)map.get(clazz.getName());
		}else{
			ResponseHandler<T> responseHandler = new ResponseHandler<T>() {
				@Override
				public T handleResponse(org.apache.http.HttpResponse httpResponse) throws ClientProtocolException, IOException {
					int status = httpResponse.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = (HttpEntity) httpResponse.getEntity();
						String str = EntityUtils.toString((HttpEntity) entity);
						//return JsonUtil.parseObject(new String(str.getBytes("iso-8859-1"), "utf-8"), clazz);
						return JsonUtil.parseObject(str, clazz);
					} else {
						throw new ClientProtocolException("Unexpected response status: " + status);
					}
				}

			};
			map.put(clazz.getName(), responseHandler);
			return responseHandler;
		}
	}

}
