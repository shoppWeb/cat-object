package sun.cat.control.root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 后台登录
 * Created by 小小工作者 on 2016/5/25.
 */
@Controller
@RequestMapping("/root/admin/rootIndexControl")
public class RootIndexControl {
    static Logger logger = LogManager.getLogger();
    /**
     * 登录首页
     * @return
     */
    @RequestMapping(value = "/indexView")
    public ModelAndView indexView(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ROOT/index");
        return modelAndView;
    }



}
