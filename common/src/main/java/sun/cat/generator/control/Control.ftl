package sun.cat.control;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.cat.common.model.ExecuteResult;

import sun.cat.entity.${entity.entityName};
import sun.cat.service.I${entity.entityName?cap_first}Service;
/**
*
* Created by 小小工作者 on ${.now}.
*/
@Controller
@RequestMapping("/${entity.entityName?uncap_first}Control")
public class ${entity.entityName}Control {
    static Logger logger = LogManager.getLogger();

@Autowired
private I${entity.entityName?cap_first}Service ${entity.entityName?uncap_first}Service;


@RequestMapping(value = "/pageView")
public ModelAndView pageView(){
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("${entity.entityName?uncap_first}");
    return modelAndView;
}

}
