package cn.com.chng.erp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/main")
public class MainController {
    @RequestMapping(value = "/toMainPage")
    public ModelAndView toMainPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main/mainPage");
        return modelAndView;
    }
}
