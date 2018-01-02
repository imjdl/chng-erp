package cn.com.chng.erp.auth;

import cn.com.chng.erp.constants.Constants;
import cn.com.chng.erp.domains.SystemPrivilege;
import cn.com.chng.erp.domains.SystemUser;
import cn.com.chng.erp.services.SystemUserService;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by liuyandong on 2017/4/3.
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private SystemUserService systemUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser systemUser = systemUserService.findByCodeOrEmailOrMobile(username);
        Validate.notNull(systemUser, "用户不存在！");
        List<SystemPrivilege> systemPrivileges = systemUserService.obtainAllPrivileges(systemUser.getId());

        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for (SystemPrivilege systemPrivilege : systemPrivileges) {
            if (systemPrivilege.getPrivilegeType() == Constants.SYSTEM_PRIVILEGE_TYPE_HAS_AUTHORITY) {
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(systemPrivilege.getPrivilegeCode());
                authorities.add(simpleGrantedAuthority);
            }
        }
        CustomUserDetails customUserDetails = new CustomUserDetails(systemUser.getId(), username, systemUser.getPassword(), authorities, true, true, true, systemUser.isEnabled());
        return customUserDetails;
    }
}
