package cn.com.chng.erp.controllers;

import cn.com.chng.erp.domains.SystemPrivilege;
import cn.com.chng.erp.services.SystemUserService;
import cn.com.chng.erp.utils.ApplicationHandler;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/main")
public class MainController {
    @Autowired
    private SystemUserService systemUserService;

    @RequestMapping(value = "/toMainPage")
    public ModelAndView toMainPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main/mainPage");

        List<SystemPrivilege> systemPrivileges = systemUserService.obtainAllPrivileges(ApplicationHandler.getUserId());
        Map<SystemPrivilege, List<SystemPrivilege>> menus = new LinkedHashMap<SystemPrivilege, List<SystemPrivilege>>();
        List<SystemPrivilege> oneLevelMenus = new ArrayList<SystemPrivilege>();
        Map<BigInteger, List<SystemPrivilege>> twoLevelMenus = new LinkedHashMap<BigInteger, List<SystemPrivilege>>();
        for (SystemPrivilege systemPrivilege : systemPrivileges) {
            if (StringUtils.isBlank(systemPrivilege.getControllerName()) && StringUtils.isBlank(systemPrivilege.getActionName()) && systemPrivilege.getId() != BigInteger.ONE) {
                oneLevelMenus.add(systemPrivilege);
            }
            if ("index".equals(systemPrivilege.getActionName())) {
                List<SystemPrivilege> privileges = twoLevelMenus.get(systemPrivilege.getParentId());
                if (CollectionUtils.isEmpty(privileges)) {
                    privileges = new ArrayList<SystemPrivilege>();
                    twoLevelMenus.put(systemPrivilege.getParentId(), privileges);
                }
                privileges.add(systemPrivilege);
            }
        }
        for (SystemPrivilege systemPrivilege : oneLevelMenus) {
            menus.put(systemPrivilege, twoLevelMenus.get(systemPrivilege.getId()));
        }

        modelAndView.addObject("menus", menus);
        modelAndView.addObject("userName", ApplicationHandler.getUserName());
        modelAndView.addObject("userType", ApplicationHandler.getUserType());
        modelAndView.addObject("powerStationName", ApplicationHandler.getPowerStationName());
        return modelAndView;
    }
}
