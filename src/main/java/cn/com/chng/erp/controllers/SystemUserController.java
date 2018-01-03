package cn.com.chng.erp.controllers;

import cn.com.chng.erp.basic.BasicController;
import cn.com.chng.erp.models.systemuser.ListModel;
import cn.com.chng.erp.services.SystemUserService;
import cn.com.chng.erp.utils.ApplicationHandler;
import cn.com.chng.erp.utils.GsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.Map;

@Controller
@RequestMapping(value = "/systemUser")
public class SystemUserController extends BasicController {
    @Autowired
    private SystemUserService systemUserService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("systemUser/index");
        return modelAndView;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public String list() throws InstantiationException, IllegalAccessException, ParseException, NoSuchFieldException {
        Map<String, String> requestParameters = ApplicationHandler.getRequestParameters();
        ListModel listModel = ApplicationHandler.instantiateObject(ListModel.class, requestParameters);
        listModel.validateAndThrow();
        return GsonUtils.toJson(systemUserService.list(listModel));
    }
}
