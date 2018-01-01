package cn.com.chng.erp.configurations;

import cn.com.chng.erp.auth.CustomUserDetailsService;
import cn.com.chng.erp.auth.HuaNengFilterInvocationSecurityMetadataSource;
import cn.com.chng.erp.handlers.AuthenticationFailureHandler;
import cn.com.chng.erp.handlers.AuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by liuyandong on 2017/4/28.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().disable()
                .authorizeRequests()
                .antMatchers("/login/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login/login").usernameParameter("loginName").passwordParameter("password").loginProcessingUrl("/login/doLogin").successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler)
                .and()
                .logout().logoutUrl("/login/logout").logoutSuccessUrl("/login/login").invalidateHttpSession(true);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/images/**", "/javascripts/**", "/libraries/**", "/stylesheets/**", "/favicon.ico");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(new Md5PasswordEncoder());
    }

    @Bean(initMethod = "init")
    public HuaNengFilterInvocationSecurityMetadataSource huaNengFilterInvocationSecurityMetadataSource() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return new HuaNengFilterInvocationSecurityMetadataSource();
    }
}
