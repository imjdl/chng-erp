package cn.com.chng.erp.controllers;

import cn.com.chng.erp.basic.BasicController;
import cn.com.chng.erp.utils.ApplicationHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/login")
public class LoginController extends BasicController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        Integer userType = ApplicationHandler.getUserType();
        ModelAndView modelAndView = new ModelAndView();
        if (userType != null) {
            modelAndView.setViewName("redirect:../main/toMainPage");
        } else {
            modelAndView.setViewName("login/index");
        }
        return modelAndView;
    }
}
