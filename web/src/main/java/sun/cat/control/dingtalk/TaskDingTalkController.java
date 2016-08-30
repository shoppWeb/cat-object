package sun.cat.control.dingtalk;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.cat.common.extend.dingtalk.aes.DingTalkJsApiSingnature;
import sun.cat.common.extend.dingtalk.api.JsApiTickenAPI;
import sun.cat.common.extend.dingtalk.api.TokenAPI;
import sun.cat.common.extend.dingtalk.bean.JsApiTicket;
import sun.cat.common.extend.dingtalk.bean.common.AccessToken;
import sun.cat.common.extend.dingtalk.model.DingTalkViewCredentialModel;
import sun.cat.common.model.ExecuteResult;
import sun.cat.utils.properties.PropertiesUtil;

/**
 * 获取 钉钉签名接口
 */
@Controller
@RequestMapping("/dingTalkTask")
public class TaskDingTalkController {
	static Logger logger = LogManager.getLogger();

	@RequestMapping(value = "/getDingTalkViewCredential")
	@ResponseBody
	public ExecuteResult<DingTalkViewCredentialModel> getDingTalkViewCredential(String viewUrl){
		viewUrl = viewUrl.replace("@@@@" , "&");
		ExecuteResult executeResult = new ExecuteResult();
		DingTalkViewCredentialModel dingTalkViewCredentialModel = new DingTalkViewCredentialModel();
		try{

			AccessToken accessToken = TokenAPI.accessToken(PropertiesUtil.getPropertyValue("conf/conf.properties", "dingtalk.corpid"), PropertiesUtil.getPropertyValue("conf/conf.properties", "dingtalk.CorpSecret"));
			if (accessToken.getAccess_token() != null) {
				JsApiTicket token = JsApiTickenAPI.getJsApiTicket(accessToken.getAccess_token());

				if (token.getTicket() != null) {
					dingTalkViewCredentialModel.setUrl(viewUrl);
					dingTalkViewCredentialModel.setTikcet(token.getTicket());
					dingTalkViewCredentialModel.setTimeStamp(System.currentTimeMillis() / 1000);

					String jsApiSingnature = DingTalkJsApiSingnature.getJsApiSingnature(viewUrl, dingTalkViewCredentialModel.getNonce(), dingTalkViewCredentialModel.getTimeStamp(), token.getTicket());
					dingTalkViewCredentialModel.setTikcet(jsApiSingnature);

				}

			}


			executeResult.setIsSuccee(true);
			executeResult.setDes("成功");
			executeResult.setResult(dingTalkViewCredentialModel);
		}catch (Exception e){
			executeResult.setDes("失败");
		}
		return executeResult;
	}

	@RequestMapping(value = "/index")
	public ModelAndView getIndex(){
		logger.info("你好世界");
		return new ModelAndView("ROOT/index");
	}
}