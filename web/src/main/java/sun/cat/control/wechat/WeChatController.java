package sun.cat.control.wechat;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.cat.utils.XMLConverUtil;
import weixin.popular.bean.message.EventMessage;
import weixin.popular.bean.xmlmessage.XMLTextMessage;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Created by 小小工作者 on 2016/6/28.
 */
@Controller
@RequestMapping("/wechat")
public class WeChatController {
//    static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Value("${wechat.appid:null}")
    private String ippid;
    @Value("${wechat.appsecret:null}")
    private String appsecret;
    @Value("${wechat.token:null}")
    private String token;



    @RequestMapping("/receiveRequest")
    @ResponseBody
    public void receiveRequest(@RequestParam(value = "signature" , defaultValue = "") String signature ,
                                 @RequestParam(value = "timestamp" , defaultValue = "") String timestamp ,
                                 @RequestParam(value = "nonce" , defaultValue = "") String nonce ,
                                 HttpServletRequest request , HttpServletResponse response) {

        ServletInputStream inputStream = null;
        ServletOutputStream outputStream = null;
        try {
            String echostr = request.getParameter("echostr");
            request.setCharacterEncoding("utf-8");
            inputStream = request.getInputStream();
            outputStream = response.getOutputStream();
            //首次请求申请验证,返回echostr
            if (echostr != null) {
                outputStreamWrite(outputStream, echostr);
                return ;
            }

            EventMessage eventMessage = null;
            if (inputStream != null) {
                //转换XML
                eventMessage = XMLConverUtil.convertToObject(EventMessage.class, inputStream , Charset.forName("utf-8"));
                String expireKey = eventMessage.getFromUserName() + "__"
                        + eventMessage.getToUserName() + "__"
                        + eventMessage.getMsgId() + "__"
                        + eventMessage.getCreateTime();
                System.err.println("收到的信息为:" + JSON.toJSONString(eventMessage));

            }

            switch (eventMessage.getMsgType()){
                case "text" :
                    //文本消息
                    break;
                case "image" :
                    //图片消息
                    break;
                case "voice" :
                    //语音消息
                    break;
                case "video" :
                    //视频消息
                    break;
                case "shortvideo":
                    //小视频消息
                    break;
                case "location" :
                    //地理位置消息
                    break;
                case "link":
                    //链接消息
                    break;
                case "event" :
                    //事件消息

                    switch (eventMessage.getEvent()){
                        case "CLICK" :
                            //菜单点击事件
                            break;
                        case "LOCATION" :
                            //上报地理位置事件
                            break;
                        case "subscribe" :
                            //用户未关注时，进行关注后的事件推送
                            break;
                        case "unsubscribe" :
                            //用户取消关注
                            break;
                        case "SCAN" :
                            //用户已关注时的事件推送
                            break;
                    }

                    break;
            }

            XMLTextMessage xmlTextMessage = new XMLTextMessage(
                    eventMessage.getFromUserName(),
                    eventMessage.getToUserName(),
                    eventMessage.getContent());

            xmlTextMessage.outputStreamWrite(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 数据流输出
     * @param outputStream
     * @param text
     * @return
     */
    private boolean outputStreamWrite(OutputStream outputStream,String text){
        try {
            outputStream.write(text.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
