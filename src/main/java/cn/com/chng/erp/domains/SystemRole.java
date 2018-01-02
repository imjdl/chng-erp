package cn.com.chng.erp.domains;

import cn.com.chng.erp.basic.BasicDomain;

public class SystemRole extends BasicDomain {
    private String serviceName;
    private String roleCode;
    private String roleName;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
