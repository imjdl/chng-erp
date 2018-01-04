package cn.com.chng.erp.controllers;

import cn.com.chng.erp.api.ApiRest;
import cn.com.chng.erp.services.SystemPrivilegeService;
import cn.com.chng.erp.utils.GsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/systemPrivilege")
public class SystemPrivilegeController {
    @Autowired
    private SystemPrivilegeService systemPrivilegeService;

    @RequestMapping(value = "/findAll")
    @ResponseBody
    public String findAll() {
        ApiRest apiRest = systemPrivilegeService.findAll();
        return GsonUtils.toJson(apiRest);
    }
}
