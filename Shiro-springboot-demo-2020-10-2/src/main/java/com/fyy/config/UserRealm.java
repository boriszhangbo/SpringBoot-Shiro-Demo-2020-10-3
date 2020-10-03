package com.fyy.config;

import com.fyy.pojo.User;
import com.fyy.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *  @ClassName: UserRealm   
 *  @Description: 
 *  @Author: fu yuanyuan
 *  @CreateDate: 2020/10/2 19:55
 *  @UpdateUser:    
 *  @UpdateDate: 
 *  @UpdateRemark:    
 *  @Version: 1.0   
 */
// 自定义的 UserRealm
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了 => 授权");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

//        给用户授权：user:add
        info.addStringPermission("user:add");
        info.addStringPermission("user:update");

        // 拿到当前登录的对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User)subject.getPrincipal();

        // 设置当前用户权限
        info.addStringPermission(currentUser.getPerms());
        return info;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了 => 认证");

        // 用户名认证
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        // 连接数据库
        User user = userService.queryUserByName(userToken.getUsername());

        if (user == null){
            return null;        // UnknownAccountException
        }

        //存session
        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        session.setAttribute("loginUser",user);

        // 密码认证，shiro 去做，SimpleAuthenticationInfo是接口AuthenticationInfo的实现类
        return new SimpleAuthenticationInfo(user,user.getPwd(),"");
    }
}
