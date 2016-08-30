package sun.cat.control.root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import sun.cat.common.model.ExecuteResult;
import sun.cat.service.IRootUserService;

import javax.servlet.http.HttpServletRequest;

/**
 * 后台登录
 * Created by 小小工作者 on 2016/5/25.
 */
@Controller
@RequestMapping("/root/login/rootLoginControl")
@SessionAttributes("userId")
public class RootLoginControl {
    static Logger logger = LogManager.getLogger();

    @Autowired
    private IRootUserService rootUserService;

    /**
     * 登录首页
     * @return
     */
    @RequestMapping(value = "/loginView")
    public ModelAndView loginView(@ModelAttribute("userId")String userId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ROOT/login");
        return modelAndView;
    }

    @RequestMapping(value = "/loginVerify")
    @ResponseBody
    public ExecuteResult loginVerify(String userName , String passWord){
        ExecuteResult executeResult = new ExecuteResult();

        executeResult.setIsSuccee(true);
        executeResult.setDes("登录成功");
        executeResult.setResult("/root/admin/rootIndexControl/indexView");
        return executeResult;
    }

}
