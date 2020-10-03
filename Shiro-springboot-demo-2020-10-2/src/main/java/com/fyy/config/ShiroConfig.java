package com.fyy.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  @ClassName: ShiroConfig   
 *  @Description: 
 *  @Author: fu yuanyuan
 *  @CreateDate: 2020/10/2 19:54
 *  @UpdateUser:    
 *  @UpdateDate: 
 *  @UpdateRemark:    
 *  @Version: 1.0   
 */
@Configuration
public class ShiroConfig {

    // ShiroFilterFactoryBean
    // 3 关联 DefaultWebSecurityManager
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        // 添加 shiro 的内置过滤器
        /**
         * anon：无需认证就可以访问
         * authc：必须认证了才能访问
         * user：必须拥有 记住我 功能才能用
         * perms：拥有对某个资源的权限才能访问
         * role：拥有某个角色权限才能访问
         */

        // 登录拦截
        Map<String, String> filteMap = new LinkedHashMap<>();

        // 授权，携带 user:add 资源的用户才能访问
        filteMap.put("/user/add","perms[user:add]");
        filteMap.put("/user/update","perms[user:update]");

//        filteMap.put("/user/add","authc");
//        filteMap.put("/user/update","authc");
        filteMap.put("/user/*","authc");

        bean.setFilterChainDefinitionMap(filteMap);

        // 设置登录请求，如无权限跳转到登录页面
        bean.setLoginUrl("/toLogin");

        //未授权页面跳转
        bean.setUnauthorizedUrl("/noauth");

        return bean;
    }

    // DefaultWebSecurityManager

    // 2 让 UserRealm 与 DefaultWebSecurityManager 绑定
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联 UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    // Realm

    // 1 创建 Realm 对象，需自定义类
    // 1.1 将 UserRealm 注入到 ShiroConfig 中，让 Spring 托管
    @Bean(name = "userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }

    // 整合 ShiroDialect：用来整合 shiro thymeleaf
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
