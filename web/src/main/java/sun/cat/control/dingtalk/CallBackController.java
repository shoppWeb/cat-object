package sun.cat.control.dingtalk;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.cat.common.extend.dingtalk.aes.DingTalkEncryptException;
import sun.cat.common.extend.dingtalk.aes.DingTalkEncryptor;
import sun.cat.utils.properties.PropertiesUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * 钉钉回调Controller入口
 * Created by 小小工作者 on 2016/5/17.
 */
@Controller
@RequestMapping("/callBack")
public class CallBackController {

    @RequestMapping(value = "/userCallBack")
    @ResponseBody
    public void findBaseCity(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        /**url中的签名**/
        String msgSignature = request.getParameter("signature");
        /**url中的时间戳**/
        String timeStamp = request.getParameter("timestamp");
        /**url中的随机字符串**/
        String nonce = request.getParameter("nonce");

        /**post数据包数据中的加密数据**/
        ServletInputStream sis =  request.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(sis));
        String line ;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line);
        }
        JSONObject jsonEncrypt = JSONObject.parseObject(sb.toString());
        String encrypt = jsonEncrypt.getString("encrypt");

//        /**对encrypt进行解密**/
        DingTalkEncryptor dingTalkEncryptor = null;
        String plainText = null;
        try {
            //对于DingTalkEncryptor的第三个参数，ISV进行配置的时候传对应套件的SUITE_KEY，普通企业传Corpid
            dingTalkEncryptor = new DingTalkEncryptor("changzheng", "1234567890123456789012345678901234567890123", PropertiesUtil.getPropertyValue("conf/conf.properties", "dingtalk.corpid"));
            plainText = dingTalkEncryptor.getDecryptMsg(msgSignature, timeStamp, nonce, encrypt);
        } catch (DingTalkEncryptException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        /**对从encrypt解密出来的明文进行处理**/
        JSONObject plainTextJson = JSONObject.parseObject(plainText);
        String eventType = plainTextJson.getString("EventType");
        System.err.println(JSONObject.toJSONString(plainTextJson));
        switch (eventType){
            case "org_user_add"://do something
                break;
            case "org_user_modify"://do something
                break;
            case "org_user_leave":// do something
                break;
            case "check_url"://do something
            default : //do something
                break;
        }

        /**对返回信息进行加密**/
        long timeStampLong = Long.parseLong(timeStamp);
        Map<String,String> jsonMap = null;
        try {
            jsonMap = dingTalkEncryptor.getEncryptedMap("success", timeStampLong, nonce);
        } catch (DingTalkEncryptException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        JSONObject json = new JSONObject();
        json.putAll(jsonMap);
        response.getWriter().append(json.toString());

    }

}
