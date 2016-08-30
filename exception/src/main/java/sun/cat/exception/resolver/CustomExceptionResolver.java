package sun.cat.exception.resolver;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import sun.cat.exception.exception.CustomException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 小小工作者 on 2016/6/18.
 */
public class CustomExceptionResolver extends ACustomExceptionResolver implements ICustomExceptionResolver,HandlerExceptionResolver {

    /**
     * （非 Javadoc）
     * <p>Title: resolveException</p>
     * <p>Description: </p>
     * @param request
     * @param response
     * @param handler
     * @param ex 系统 抛出的异常
     * @return
     * @see org.springframework.web.servlet.HandlerExceptionResolver#resolveException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //handler就是处理器适配器要执行Handler对象（只有method）

        //解析出异常类型
        //如果该 异常类型是系统 自定义的异常，直接取出异常信息，在错误页面展示

        //上边代码变为
        CustomException customException = null;
        if(ex instanceof CustomException){
            customException = (CustomException)ex;
        }else{
            customException = new CustomException("未知错误");
        }

        //错误信息
        String message = customException.getMessage();

        ModelAndView modelAndView = new ModelAndView();

        //将错误信息传到页面
        modelAndView.addObject("message", message);

        //指向错误页面
        modelAndView.setViewName("ROOT/common/exception");


        return modelAndView;
    }
}
