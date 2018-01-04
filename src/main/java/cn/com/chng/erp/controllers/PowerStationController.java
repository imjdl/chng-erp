package cn.com.chng.erp.controllers;

import cn.com.chng.erp.models.powerstation.ListModel;
import cn.com.chng.erp.services.PowerStationService;
import cn.com.chng.erp.utils.ApplicationHandler;
import cn.com.chng.erp.utils.GsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.Map;

@Controller
@RequestMapping(value = "/powerStation")
public class PowerStationController {
    @Autowired
    private PowerStationService powerStationService;

    @RequestMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("powerStation/index");
        return modelAndView;
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public String list() throws InstantiationException, IllegalAccessException, ParseException, NoSuchFieldException {
        Map<String, String> requestParameters = ApplicationHandler.getRequestParameters();
        ListModel listModel = ApplicationHandler.instantiateObject(ListModel.class, requestParameters);
        listModel.validateAndThrow();
        return GsonUtils.toJson(powerStationService.list(listModel));
    }
}
