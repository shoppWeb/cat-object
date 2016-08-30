package sun.cat.control;

import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.asm.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.cat.entity.User;
import sun.cat.entity.UserModel;
import sun.cat.exception.exception.CustomException;
import sun.cat.service.IUserService;
import sun.cat.test.CustomizeScanTest;
import sun.cat.test.ScanClass1;
import sun.cat.utils.spring.SpringContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * Created by 小小工作者 on 2016-6-12 17:00:25.
 */
@RestController
@RequestMapping("/userControl")
@SessionAttributes("userId")
public class UserControl{
    static Logger logger = LogManager.getLogger();

    @Autowired
    private IUserService userService;


    @RequestMapping(value = "/pageView")
    public ModelAndView pageView(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        request.getSession().setAttribute("userId", "孙海龙");
//        model.addAttribute("userId", "孙海龙");
        User user = null;
        try{
            modelAndView.setViewName("user");
            user = userService.selectByPrimaryKey(1);
            modelAndView.addObject("user" , user);

        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
        return modelAndView;
    }
    @RequestMapping(value = "/demo")
    @ExceptionHandler(CustomException.class)
    public void demo(){
        ScanClass1 injectClass =  SpringContextUtils.getApplicationContext().getBean(ScanClass1.class);
        injectClass.print();
    }

    @RequestMapping(value="/requestBodyDemo" , method = RequestMethod.GET)
    public void requestBodyDemo(@RequestBody List<User> users ,
                                @RequestHeader(name="Accept-Encoding") String name ,
                                @RequestHeader(name="userName") String userName ,
                                @CookieValue("JSESSIONID") String cookie)
            throws Exception {
        System.err.println(JSON.toJSONString(users)+name);
        throw new CustomException("错错了");
    }


        @RequestMapping("/redirectView")
    public String redirectView(@RequestParam("userName")String userName ,
                               RedirectAttributes redirectAttributes ,
                               @ModelAttribute("userId")String userId){
        redirectAttributes.addFlashAttribute("name" , "大王");
        redirectAttributes.addAttribute("age" , 123);
        logger.info(userName);
        System.out.println("Hello World!");
        return "redirect:/userControl/pageView";
    }

    @RequestMapping("/validUser")
    @ResponseBody
    public String validUser(@Valid @RequestBody UserModel user , BindingResult bindingResult){
        logger.info(user);

        if (bindingResult.hasErrors()) {
            System.out.println("验证失败！");
        }
//        if(bindingResult.getErrorCount()>0){
//          List<ObjectError> objectErrors = bindingResult.getAllErrors();
//            for(ObjectError objectError : objectErrors){
//                logger.error(objectError.getDefaultMessage());
//            }
//
//        }
        return "test";
    }

    @RequestMapping("/test99")
//    @ResponseBody
    public String getViewIndex9() {
        return "休闲鞋";
    }

    public static void main(String args[]){
        List<User> users = new ArrayList<>();
        users.add(new User("孙海龙"));
        users.add(new User("小明"));
        users.add(new User("小王"));
        System.err.println(JSON.toJSONString(users));
    }


    //At the time of initialization,convert the type "String" to type "date"
    //使用InitBinder来处理Date类型的参数
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder){
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    @RequestMapping("redirect")
    public String redirectView(){

        return "redirect:/pageView";
    }

//    @ModelAttribute
//    public void preRun() {
//        System.out.println("Test Pre-Run:损害了那个区委区委去问去问去问去问全文");
//    }

    @RequestMapping("/FlowEdit")
    public ModelAndView FlowEdit(HttpServletRequest request , HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView("Flow/FlowEdit");

        return modelAndView;
    }

}
